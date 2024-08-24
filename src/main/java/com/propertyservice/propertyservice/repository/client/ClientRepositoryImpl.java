package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.building.QBuilding;
import com.propertyservice.propertyservice.domain.building.QBuildingAddress;
import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.client.QClientRemark;
import com.propertyservice.propertyservice.domain.common.QAddressLevel1;
import com.propertyservice.propertyservice.domain.common.QAddressLevel2;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.property.QShowingProperty;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.utils.BooleanExpressionBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QClient client = QClient.client;
    private final QManager manager = QManager.manager;
    private final QClientRemark clientRemark = QClientRemark.clientRemark;
    private final QShowingProperty showingProperty = QShowingProperty.showingProperty;
    private final QProperty property = QProperty.property;
    private final QCompany company = QCompany.company;
    private final QBuilding building = QBuilding.building;
    private final QBuildingAddress buildingAddress = QBuildingAddress.buildingAddress;
    private final QAddressLevel1 addressLevel1 = QAddressLevel1.addressLevel11;
    private final QAddressLevel2 addressLevel2 = QAddressLevel2.addressLevel21;

    // 고객 목록 조회
    @Override
    public List<ClientDto.ClientListResponseDto> searchClientList(String clientName, String clientPhoneNumber) {
        return  queryFactory
                .select(
                        new QClientDto_ClientListResponseDto(
                                client.clientId,
                                manager.managerName,
                                client.clientName
                        )
                    )
                .from(client).leftJoin(manager).on(client.managerId.eq(manager.managerId))
                .where(
                        BooleanExpressionBuilder.orBooleanBuilder(
                                clientPhoneNumberLike(clientPhoneNumber),
                                clientNameLike(clientName)
                        )
                )
                .fetch();

    }

    @Override
    public List<ShowingPropertySummaryDto> searchShowingPropertyList(Long clientId) {
        return queryFactory
                .select(
                        new QShowingPropertySummaryDto(
                                showingProperty.propertyId,
                                property.transactionType,
                                Expressions.stringTemplate(
                                        "concat_ws(' ', {0}, {1}, {2}, {3})",
                                        addressLevel1.addressLevel1,
                                        addressLevel2.addressLevel2,
                                        buildingAddress.addressLevel3
                                ).as("address"),
                                new CaseBuilder()
                                        .when(property.transactionType.eq(TransactionType.MONTHLY).or(property.transactionType.eq(TransactionType.SHORTERM)))
                                        .then(Expressions.stringTemplate("CONCAT({0}, '/', {1})", property.deposit, property.monthlyFee))
                                        .when(property.transactionType.eq(TransactionType.JEONSE).or(property.transactionType.eq(TransactionType.TRADE)))
                                        .then(property.jeonseFee.stringValue())
                                        .otherwise("")
                                        .as("price")
                        )
                )
                .from(client)
                .leftJoin(showingProperty).on(client.clientId.eq(showingProperty.clientId))
                .leftJoin(property).on(property.propertyId.eq(showingProperty.propertyId))
                .leftJoin(building).on(property.building.eq(building))
                .leftJoin(buildingAddress).on(buildingAddress.eq(buildingAddress))
                .leftJoin(addressLevel1).on(buildingAddress.addressLevel1Id.eq(addressLevel1.addressLevel1Id))
                .leftJoin(addressLevel2).on(buildingAddress.addressLevel2Id.eq(addressLevel2.addressLevel2Id))
                .where(client.clientId.eq(clientId))
                .fetch();
    }

    //@Override
    public List<ClientRemarkDto> searchClientRemark(Long clientId) {
        return queryFactory
                .select(
                        new QClientRemarkDto(
                                clientRemark.clientRemarkId,
                                manager.managerName,
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

    @Override
    public List<ClientDto.ClientListDto> searchClientList(Long companyId) {
        return queryFactory
                .select(
                        new QClientDto_ClientListDto(
                                client.clientId,
                                client.clientName
                        )
                )
                .from(client)
                .leftJoin(manager).on(client.managerId.eq(manager.managerId))
                .leftJoin(company).on(company.eq(manager.company))
                .where(manager.company.companyId.eq(companyId))
                .fetch();
    }

    private BooleanExpression clientPhoneNumberLike(String clientPhoneNumber) {
        return hasText(clientPhoneNumber) ? client.clientPhoneNumber.like('%' + clientPhoneNumber + '%') : null;
    }
    private BooleanExpression clientNameLike(String clientName) {
        return hasText(clientName) ? client.clientName.like('%' + clientName + '%') : null;
    }
}
