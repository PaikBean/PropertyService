package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.client.QClientRemark;
import com.propertyservice.propertyservice.domain.company.QManager;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.schedule.QSchedule;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.dto.schedule.ScheduleCondition;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.repository.property.PropertyRepositoryCustom;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;

import java.util.List;

@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryCustom, ScheduleRepositoryCustom, PropertyRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QClient client = QClient.client;
    private final QManager manager = QManager.manager;
    private final QClientRemark clientRemark = QClientRemark.clientRemark;
    private final QProperty property = QProperty.property;
    private final QSchedule schedule = QSchedule.schedule;

    // 고객 목록 조회
    @Override
    public List<ClientDto.ClientListResponseDto> searchClientList(Long managerId, String clientPhoneNumber) {
        return  queryFactory
                .select(
                        Projections.constructor(ClientDto.ClientListResponseDto.class,
                            client.managerId,
                            manager.managerName,
                            client.clientName,
                            client.clientId
                    ))
                .from(client).leftJoin(manager).on(client.managerId.eq(manager.managerId))
                .where(
                        client.managerId.eq(managerId).and(client.clientPhoneNumber.contains(clientPhoneNumber))
                )
                .fetch();

    }

    // 고객 상세 조회.
    @Override
    public List<ClientDto.ClientDetailResponseDto> searchClientDetail(Long clientId) {
        return null;
    }

    // 고객 일정 조회
    @Override
    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        return null;
    }
    
    // 고객 매물 조회
    @Override
    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        return null;
    }

    
}
