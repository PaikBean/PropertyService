package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.schedule.QSchedule;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.QScheduleSummaryDto;
import com.propertyservice.propertyservice.dto.schedule.ScheduleCondition;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.utils.BooleanExpressionBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    //private final QProperty property = QProperty.property;
    private final QSchedule schedule = QSchedule.schedule;
    private final QClient client =QClient.client;
    private final QManager manager= QManager.manager;


    @Override
    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        return queryFactory
                .select(
                        new QScheduleSummaryDto(
                                schedule.scheduleId,
                                schedule.managerId,
                                manager.managerName, // Todo : manager 엔티티 추가되면 manager Name 넣기
                                schedule.clientId,
                                client.clientName, // Todo : client 엔티티 추가되면 client Name 넣기
                                schedule.scheduleType,
                                schedule.priority.stringValue(),
                                schedule.scheduleDate
                        )
                )
                .from(schedule)
                .leftJoin(manager).on(schedule.managerId.eq(manager.managerId))
                .leftJoin(client).on(schedule.clientId.eq(client.clientId))
                .where(
                        BooleanExpressionBuilder.andBooleanBuilder(
                                scheduleTypeEq(scheduleCondition.getScheduleType()),
                                scheduleDateBetween(scheduleCondition.getStartDate(), scheduleCondition.getEndDate()),
                                managerIdEq(scheduleCondition.getManagerId()), // Todo : manager 엔티티 추가되면 manager 조건 넣기
                                clientIdEq(scheduleCondition.getClientId())// Todo : client 엔티티 추가되면 client 조건 넣기
                        )
                )
                .fetch();
    }

    // ClientId로 일정 가져옴.
    @Override
    public List<ScheduleSummaryDto> searchScheduleList(Long clientId) {
        return queryFactory
                .select(
                        new QScheduleSummaryDto(
                                schedule.scheduleId,
                                schedule.managerId,
                                manager.managerName,// Todo : manager 엔티티 추가되면 manager Name 넣기
                                client.clientId,// Todo : client 엔티티 추가되면 client Name 넣기
                                client.clientName,
                                schedule.scheduleType,
                                schedule.priority.stringValue(),
                                schedule.scheduleDate
                        )
                )
                .from(client)
                .leftJoin(schedule).on(client.clientId.eq(schedule.clientId))
                .leftJoin(manager).on(manager.managerId.eq(schedule.managerId))
                .where(client.clientId.eq(clientId))
                .fetch();
    }

    private BooleanExpression scheduleTypeEq(ScheduleType scheduleType){
        return scheduleType != null ? schedule.scheduleType.eq(scheduleType) : null;
    }
    private BooleanExpression scheduleDateBetween(LocalDateTime startDate, LocalDateTime endDate){
        return startDate != null && endDate != null ? schedule.scheduleDate.between(startDate, endDate) : null;
    }

    private BooleanExpression managerIdEq(Long managerId){
        return managerId != null ? manager.managerId.eq(managerId) : null;
    }
    private BooleanExpression clientIdEq(Long clientId){
        return clientId != null ? client.clientId.eq(clientId) : null;
    }
}
