package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.schedule.QSchedule;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.dto.schedule.QScheduleSummaryDto;
import com.propertyservice.propertyservice.dto.schedule.ScheduleCondition;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.service.CommonService;
import com.propertyservice.propertyservice.utils.BooleanExpressionBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final CommonService commonService;
    //private final QProperty property = QProperty.property;
    private final QSchedule schedule = QSchedule.schedule;
    private final QClient client = QClient.client;
    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;


    @Override
    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        return queryFactory
                .select(
                        new QScheduleSummaryDto(
                                schedule.scheduleId,
                                manager.managerName,
                                schedule.scheduleType,
                                schedule.priority.stringValue(),
                                schedule.scheduleDate
                        )
                )
                .from(schedule)
                .join(manager).on(schedule.managerId.eq(manager.managerId))
                .join(company).on(manager.company.companyId.eq(company.companyId))
                .where(
                        company.companyId.eq(scheduleCondition.getCompanyId()),
                        BooleanExpressionBuilder.andBooleanBuilder(
                                scheduleTypeEq(scheduleCondition.getScheduleType()),
                                scheduleDateGoe(scheduleCondition.getStartDate()),
                                scheduleDateLoe(scheduleCondition.getEndDate()),
                                managerIdEq(scheduleCondition.getManagerId()),
                                clientIdEq(scheduleCondition.getClientId())
                        )
                ).fetch();
    }

    // ClientId로 일정 가져옴.
    @Override
    public List<ScheduleSummaryDto> searchScheduleList(Long clientId) {
        CustomUserDetail customUserDetail = commonService.getCustomUserDetailBySecurityContextHolder();
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
                .join(schedule).on(client.clientId.eq(schedule.clientId))
                .join(manager).on(manager.managerId.eq(schedule.managerId))
                //로그인한 회사 조건 추가.
                .join(company).on(manager.company.eq(company).and(company.eq(customUserDetail.getCompany())))
                .where(client.clientId.eq(clientId))
                .fetch();
    }

    @Override
    public List<ScheduleSummaryDto> searchScheduleListByClientId(Long clientId) {
        CustomUserDetail customUserDetail = commonService.getCustomUserDetailBySecurityContextHolder();
        return queryFactory
                .select(
                        new QScheduleSummaryDto(
                                schedule.scheduleId,
                                manager.managerName,
                                schedule.scheduleType,
                                schedule.scheduleDate,
                                schedule.remark
                        )
                )
                .from(schedule)
                .join(manager).on(schedule.managerId.eq(manager.managerId))
                //로그인한 회사 조건 추가.
                .join(company).on(manager.company.eq(company).and(company.eq(customUserDetail.getCompany())))
                .join(client).on(schedule.clientId.eq(client.clientId))
                .where(schedule.clientId.eq(clientId))
                .fetch();
    }

    private BooleanExpression scheduleTypeEq(ScheduleType scheduleType) {
        return scheduleType != null ? schedule.scheduleType.eq(scheduleType) : null;
    }

    private BooleanExpression scheduleDateBetween(String startDate, String endDate) {
        return (startDate != null && endDate != null) && (startDate.isEmpty() && endDate.isEmpty()) ? schedule.scheduleDate.between(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay(),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay()
        ) : null;
    }

    private BooleanExpression scheduleDateGoe(String startDate) {
        if (startDate != null && !startDate.isEmpty()) {
            LocalDateTime startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
            return schedule.scheduleDate.goe(startDateTime);
        }
        return null;
    }

    private BooleanExpression scheduleDateLoe(String endDate) {
        if (endDate != null && !endDate.isEmpty()) {
            LocalDateTime endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
            return schedule.scheduleDate.loe(endDateTime);
        }
        return null;
    }

    private BooleanExpression managerIdEq(Long managerId) {
        return managerId != null ? manager.managerId.eq(managerId) : null;
    }

    private BooleanExpression clientIdEq(Long clientId) {
        return clientId != null ? client.clientId.eq(clientId) : null;
    }
}
