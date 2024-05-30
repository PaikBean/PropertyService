package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.schedule.QSchedule;
import com.propertyservice.propertyservice.domain.schedule.QScheduleType;
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
    private final QProperty property = QProperty.property;
    private final QSchedule schedule = QSchedule.schedule;
    private final QScheduleType scheduleType = QScheduleType.scheduleType1;
    @Override
    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        return queryFactory
                .select(
                        new QScheduleSummaryDto(
                                schedule.scheduleId,
                                schedule.managerId,
                                null, // Todo : manager 엔티티 추가되면 manager Name 넣기
                                schedule.clientId,
                                null, // Todo : client 엔티티 추가되면 client Name 넣기
                                schedule.scheduleType.scheduleTypeId,
                                schedule.scheduleType.scheduleType,
                                schedule.priority.stringValue(),
                                schedule.scheduleDate
                        )
                )
                .from(schedule)
                .join(scheduleType).on(
                        schedule.scheduleType.scheduleTypeId.eq(scheduleType.scheduleTypeId))
                .where(
                        BooleanExpressionBuilder.andBooleanBuilder(
                                scheduleTypeEq(scheduleCondition.getScheduleTypeId()),
                                scheduleDateBetween(scheduleCondition.getStartDate(), scheduleCondition.getEndDate())
                                // Todo : manager 엔티티 추가되면 manager 조건 넣기
                                // Todo : client 엔티티 추가되면 client 조건 넣기
                        )
                )
                .fetch();
    }

    private BooleanExpression scheduleTypeEq(Long scheduleTypeId){
        return scheduleTypeId != null ? schedule.scheduleType.scheduleTypeId.eq(scheduleTypeId) : null;
    }
    private BooleanExpression scheduleDateBetween(LocalDateTime startDate, LocalDateTime endDate){
        return startDate != null && endDate != null ? schedule.scheduleDate.between(startDate, endDate) : null;
    }
}
