package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.building.QBuilding;
import com.propertyservice.propertyservice.domain.building.QBuildingAddress;
import com.propertyservice.propertyservice.domain.building.QOwner;
import com.propertyservice.propertyservice.domain.common.QAddressLevel1;
import com.propertyservice.propertyservice.domain.common.QAddressLevel2;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.property.PropertyType;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.dto.client.QShowingPropertyCandidateDto;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateCondition;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateDto;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.service.CommonService;
import com.propertyservice.propertyservice.service.CustomUserDetailService;
import com.propertyservice.propertyservice.utils.BooleanExpressionBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final CommonService commonService;

    private final QProperty property = QProperty.property;
    private final QBuilding building = QBuilding.building;
    private final QOwner owner = QOwner.owner;
    private final QBuildingAddress buildingAddress = QBuildingAddress.buildingAddress;
    private final QAddressLevel1 addressLevel1 = QAddressLevel1.addressLevel11;
    private final QAddressLevel2 addressLevel2 = QAddressLevel2.addressLevel21;
    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;


    @Override
    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        CustomUserDetail customUserDetail = commonService.getCustomUserDetailBySecurityContextHolder();
        return queryFactory
                .select(
                        new QShowingPropertyCandidateDto(
                                property.propertyId,
                                property.transactionType,
                                //owner.ownerName,
                                Expressions.stringTemplate(
                                        "concat_ws(' ', {0}, {1}, {2}, {3})",
                                        addressLevel1.addressLevel1,
                                        addressLevel2.addressLevel2,
                                        buildingAddress.addressLevel3,
                                        property.unitNumber
                                ).coalesce("null")
                        )
                )
                .from(property)
                .join(building).on(property.building.eq(building))
                .join(buildingAddress).on(buildingAddress.eq(building.buildingAddress))
                .join(addressLevel1).on(buildingAddress.addressLevel1Id.eq(addressLevel1.addressLevel1Id))
                .join(addressLevel2).on(buildingAddress.addressLevel2Id.eq(addressLevel2.addressLevel2Id))
                // 로그인한 회사 정보만 가져오기.ß
                .join(manager).on(property.picManagerId.eq(manager.managerId))
                .join(company).on(manager.company.eq(company).and(company.eq(customUserDetail.getCompany())))
                .where(
                        BooleanExpressionBuilder.orBooleanBuilder(
                                buildingAddress1Eq(showingPropertyCandidateCondition.getAddressLevel1Id()),
                                buildingAddress2Eq(showingPropertyCandidateCondition.getAddressLevel2Id()),
                                propertyTransactionTypeEq(showingPropertyCandidateCondition.getPropertyTransactionType()),
                                propertyTypeEq(showingPropertyCandidateCondition.getPropertyType())
                        )
                )
                .fetch();
    }

    private BooleanExpression buildingAddress1Eq(Long addressLevel1Id) {
        return addressLevel1Id != null ? buildingAddress.addressLevel1Id.eq(addressLevel1Id) : null;
    }

    private BooleanExpression buildingAddress2Eq(Long addressLevel2Id) {
        return addressLevel2Id != null ? buildingAddress.addressLevel2Id.eq(addressLevel2Id) : null;
    }

    private BooleanExpression ownerNameLike(String ownerName) {
        return hasText(ownerName) ? owner.ownerName.contains(ownerName) : null;
    }

    private BooleanExpression propertyTransactionTypeEq(TransactionType transactionType) {
        return transactionType != null ? property.transactionType.eq(transactionType) : null;
    }

    private BooleanExpression propertyTypeEq(PropertyType propertyType) {
        return propertyType != null ? property.propertyType.eq(propertyType) : null;
    }
}
