package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.building.QBuilding;
import com.propertyservice.propertyservice.domain.building.QBuildingAddress;
import com.propertyservice.propertyservice.domain.building.QOwner;
import com.propertyservice.propertyservice.domain.common.QAddressLevel1;
import com.propertyservice.propertyservice.domain.common.QAddressLevel2;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.dto.client.QShowingPropertyCandidateDto;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateCondition;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateDto;
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

    private final QProperty property = QProperty.property;
    private final QBuilding building = QBuilding.building;
    private final QOwner owner = QOwner.owner;
    private final QBuildingAddress buildingAddress = QBuildingAddress.buildingAddress;
    private final QAddressLevel1 addressLevel1 = QAddressLevel1.addressLevel11;
    private final QAddressLevel2 addressLevel2 = QAddressLevel2.addressLevel21;

    @Override
    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        return null;
    }

//    @Override
//    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
//        return queryFactory
//                .select(
//                        new QShowingPropertyCandidateDto(
//                                property.propertyId,
//                                property.transactionType,
//                                owner.ownerName,
//                                Expressions.stringTemplate(
//                                        "concat_ws(' ', {0}, {1}, {2}, {3})",
//                                        addressLevel1.addressLevel1,
//                                        addressLevel2.addressLevel2,
//                                        buildingAddress.addressLevel3,
//                                        property.unitNumber
//                                )
//                        )
//                )
//                .from(property)
//                .join(building).on(property.building.eq(building))
//                .join(owner).on(building.owner.eq(owner))
//                .join(buildingAddress).on(buildingAddress.eq(buildingAddress))
//                .join(addressLevel1).on(buildingAddress.addressLevel1Id.eq(addressLevel1.addressLevel1Id))
//                .join(addressLevel2).on(buildingAddress.addressLevel2Id.eq(addressLevel2.addressLevel2Id))
//                .where(
//                        property.transactionStateId.eq(1L).and(
//                                BooleanExpressionBuilder.orBooleanBuilder(
//                                        buildingAddress1Eq(showingPropertyCandidateCondition.getAddressLevel1Id()),
//                                        buildingAddress2Eq(showingPropertyCandidateCondition.getAddressLevel2Id()),
//                                        ownerNameLike(showingPropertyCandidateCondition.getOwnerName()),
//                                        propertyTransactionTypeEq(showingPropertyCandidateCondition.getPropertyTransactionType()),
//                                        propertyTypeIdEq(showingPropertyCandidateCondition.getPropertyTypeId())
//                                )
//                        )
//                )
//                .fetch();
//    }
//
//    private BooleanExpression buildingAddress1Eq(Long addressLevel1Id) {
//        return addressLevel1Id != null ? buildingAddress.addressLevel1Id.eq(addressLevel1Id) : null;
//    }
//
//    private BooleanExpression buildingAddress2Eq(Long addressLevel2Id) {
//        return addressLevel2Id != null ? buildingAddress.addressLevel2Id.eq(addressLevel2Id) : null;
//    }
//
//    private BooleanExpression ownerNameLike(String ownerName) {
//        return hasText(ownerName) ? owner.ownerName.contains(ownerName) : null;
//    }
//
//    private BooleanExpression propertyTransactionTypeEq(TransactionType transactionType) {
//        return transactionType != null ? property.transactionType.eq(transactionType) : null;
//    }
//
//    private BooleanExpression propertyTypeIdEq(Long propertyTypeId) {
//        return propertyTypeId != null ? property.propertyTypeId.eq(propertyTypeId) : null;
//    }
}
