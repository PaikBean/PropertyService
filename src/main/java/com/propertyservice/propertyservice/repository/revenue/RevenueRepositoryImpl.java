package com.propertyservice.propertyservice.repository.revenue;

import com.propertyservice.propertyservice.domain.common.QAddressLevel1;
import com.propertyservice.propertyservice.domain.common.QAddressLevel2;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.revenue.QRevenueLedger;
import com.propertyservice.propertyservice.dto.revenue.QRevenueDto;
import com.propertyservice.propertyservice.dto.revenue.RevenueCondition;
import com.propertyservice.propertyservice.dto.revenue.RevenueDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class RevenueRepositoryImpl implements RevenueRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QRevenueLedger revenueLedger = QRevenueLedger.revenueLedger;
    private final QManager manager = QManager.manager;
    private final QAddressLevel1 addressLevel1 = QAddressLevel1.addressLevel11;
    private final QAddressLevel2 addressLevel2 = QAddressLevel2.addressLevel21;


    @Override
    public List<RevenueDto> searchRevenueList(RevenueCondition revenueCondition) {
        return queryFactory
                .select(
                        new QRevenueDto(
                                revenueLedger.id,
                                revenueLedger.managerId.managerName,
                                revenueLedger.ownerName,
                                revenueLedger.clientName,
                                Expressions.stringTemplate(
                                        "CONCAT({0}, ' ', {1}, ' ', {2})",
                                        addressLevel1.addressLevel1,
                                        addressLevel2.addressLevel2,
                                        revenueLedger.addressLevel3
                                ),
                                Expressions.stringTemplate(
                                        "DATE_FORMAT({0}, '%Y-%m-%d')",
                                        revenueLedger.contractStartDate
                                ),
                                Expressions.stringTemplate(
                                        "DATE_FORMAT({0}, '%Y-%m-%d')",
                                        revenueLedger.contractEndDate
                                ),
                                revenueLedger.transactionType,
                                new CaseBuilder()
                                        .when(revenueLedger.transactionType.eq(TransactionType.MONTHLY).or(revenueLedger.transactionType.eq(TransactionType.SHORTERM)))
                                        .then(Expressions.stringTemplate("CONCAT({0}, '/', {1})", revenueLedger.deposit, revenueLedger.monthlyFee))
                                        .when(revenueLedger.transactionType.eq(TransactionType.JEONSE).or(revenueLedger.transactionType.eq(TransactionType.TRADE)))
                                        .then(revenueLedger.jeonseFee.stringValue())
                                        .otherwise("")
                                        .as("price"),
                                revenueLedger.commission,
                                revenueLedger.remark
                        )
                )
                .from(revenueLedger)
                .join(manager).on(
                        revenueLedger.managerId.eq(manager)
                )
                .join(addressLevel1).on(
                        revenueLedger.addressLevel1Id.eq(addressLevel1.addressLevel1Id)
                )
                .join(addressLevel2).on(
                        revenueLedger.addressLevel2Id.eq(addressLevel2.addressLevel2Id)
                )
                .where(
                        managerIdEq(revenueCondition.getManagerId()),
                        addressLevel1Eq(revenueCondition.getAddressL1Id()),
                        addressLevel2Eq(revenueCondition.getAddressL2Id()),
                        contractStartDateGoe(revenueCondition.getContractStartDate()),
                        contractEndDateLoe(revenueCondition.getContractEndDate())
                )
                .fetch();
    }

    @Override
    public BigDecimal totalCommission(RevenueCondition revenueCondition) {
        return queryFactory
                .select(
                        revenueLedger.commission.sum().coalesce(BigDecimal.ZERO)
                )
                .from(revenueLedger)
                .join(manager).on(
                        revenueLedger.managerId.eq(manager)
                )
                .join(addressLevel1).on(
                        revenueLedger.addressLevel1Id.eq(addressLevel1.addressLevel1Id)
                )
                .join(addressLevel2).on(
                        revenueLedger.addressLevel2Id.eq(addressLevel2.addressLevel2Id)
                )
                .where(
                        managerIdEq(revenueCondition.getManagerId()),
                        addressLevel1Eq(revenueCondition.getAddressL1Id()),
                        addressLevel2Eq(revenueCondition.getAddressL2Id()),
                        contractStartDateGoe(revenueCondition.getContractStartDate()),
                        contractEndDateLoe(revenueCondition.getContractEndDate())
                )
                .fetchOne();
    }

    @Override
    public Long totalCount(RevenueCondition revenueCondition) {
        return queryFactory
                .select(revenueLedger.count())
                .from(revenueLedger)
                .join(manager).on(
                        revenueLedger.managerId.eq(manager)
                )
                .join(addressLevel1).on(
                        revenueLedger.addressLevel1Id.eq(addressLevel1.addressLevel1Id)
                )
                .join(addressLevel2).on(
                        revenueLedger.addressLevel2Id.eq(addressLevel2.addressLevel2Id)
                )
                .where(
                        managerIdEq(revenueCondition.getManagerId()),
                        addressLevel1Eq(revenueCondition.getAddressL1Id()),
                        addressLevel2Eq(revenueCondition.getAddressL2Id()),
                        contractStartDateGoe(revenueCondition.getContractStartDate()),
                        contractEndDateLoe(revenueCondition.getContractEndDate())
                )
                .fetchOne();
    }

    private BooleanExpression managerIdEq(Long managerId) {
        return managerId != null ? manager.managerId.eq(managerId) : null;
    }

    private BooleanExpression ownerNameContains(String ownerName) {
        return ownerName != null ? revenueLedger.ownerName.containsIgnoreCase(ownerName) : null;
    }

    private BooleanExpression addressLevel1Eq(Long addressLevel1Id) {
        return addressLevel1Id != null ? revenueLedger.addressLevel1Id.eq(addressLevel1Id) : null;
    }

    private BooleanExpression addressLevel2Eq(Long addressLevel2Id) {
        return addressLevel2Id != null ? revenueLedger.addressLevel2Id.eq(addressLevel2Id) : null;
    }

    private BooleanExpression contractStartDateGoe(String contractStartDate) {
        return hasText(contractStartDate)
                ? revenueLedger.contractStartDate.goe(LocalDateTime.parse(contractStartDate + "000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                : null;
    }

    private BooleanExpression contractEndDateLoe(String contractEndDate) {
        return hasText(contractEndDate)
                ? revenueLedger.contractStartDate.loe(LocalDateTime.parse(contractEndDate + "000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                : null;
    }

    private BooleanExpression transactionTypeEq(TransactionType transactionType) {
        return transactionType != null ? revenueLedger.transactionType.eq(transactionType) : null;
    }

}
