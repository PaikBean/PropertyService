package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.client.QClientRemark;
import com.propertyservice.propertyservice.domain.company.QManager;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.property.QPropertyRemark;
import com.propertyservice.propertyservice.domain.property.QShowingProperty;
import com.propertyservice.propertyservice.domain.schedule.QSchedule;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.dto.schedule.ScheduleCondition;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.repository.property.PropertyRepositoryCustom;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepositoryCustom;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QClient client = QClient.client;
    private final QManager manager = QManager.manager;
    private final QClientRemark clientRemark = QClientRemark.clientRemark;
    private final QShowingProperty showingProperty = QShowingProperty.showingProperty;
    private final QProperty property = QProperty.property;
    private final QPropertyRemark propertyRemark= QPropertyRemark.propertyRemark;

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

    @Override
    public List<ShowingPropertySummaryDto> searchShowingPropertyList(Long clientId, Long propertyId) {
        return queryFactory
                .select(
                        new QShowingPropertySummaryDto(
                                client.clientId,
                                showingProperty.propertyId,
                                property.unitNumber,
                                property.propertyTypeId,
                                property.transactionTypeId,
                                property.deposit,
                                property.monthlyFee,
                                property.jeonseFee,
                                property.tradeFee,
                                propertyRemark.remarkId,
                                propertyRemark.remark
                        )
                )
                .from(client)
                .leftJoin(showingProperty).on(client.clientId.eq(showingProperty.clientId))
                .leftJoin(property).on(property.propertyId.eq(showingProperty.propertyId))
                .leftJoin(propertyRemark).on(property.eq(propertyRemark.property))
                .where(client.clientId.eq(clientId).and(showingProperty.propertyId.eq(propertyId)))
                .fetch();
    }

    //@Override
    public List<ClientRemarkDto> searchClientRemark(Long clientId) {
        return queryFactory
                .select(
                        new QClientRemarkDto(
                                client.managerId,
                                manager.managerName,
                                client.clientId,
                                clientRemark.remark,
                                clientRemark.createdDate
                        )
                )
                .from(client)
                .leftJoin(clientRemark).on(client.clientId.eq(clientRemark.clientId))
                .leftJoin(manager).on(client.managerId.eq(manager.managerId))
                .where(client.clientId.eq(clientId))
                .fetch();
//        return null;
    }
}
