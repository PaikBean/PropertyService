package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.QBuilding;
import com.propertyservice.propertyservice.domain.building.QBuildingAddress;
import com.propertyservice.propertyservice.domain.building.QOwner;
import com.propertyservice.propertyservice.domain.common.QAddressLevel1;
import com.propertyservice.propertyservice.domain.common.QAddressLevel2;
import com.propertyservice.propertyservice.dto.building.BuildingCondition;
import com.propertyservice.propertyservice.dto.building.BuildingDto;
import com.propertyservice.propertyservice.dto.building.QBuildingDto;
import com.propertyservice.propertyservice.utils.BooleanExpressionBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    private final QBuilding building = QBuilding.building;
    private final QBuildingAddress buildingAddress = QBuildingAddress.buildingAddress;
    private final QOwner owner = QOwner.owner;
    private final QAddressLevel1 addressLevel1 = QAddressLevel1.addressLevel11;
    private final QAddressLevel2 addressLevel2 = QAddressLevel2.addressLevel21;

    @Override
    public List<BuildingDto> searchBuildingList(BuildingCondition buildingCondition) {
        return queryFactory
                .select(
                        new QBuildingDto(
                                building.buildingId,
                                owner.ownerName,
                                Expressions.stringTemplate(
                                        "concat_ws(' ', {0}, {1}, {2})",
                                        addressLevel1.addressLevel1,
                                        addressLevel2.addressLevel2,
                                        buildingAddress.addressLevel3
                                )
                        )
                )
                .from(owner)
                .leftJoin(building).on(owner.eq(building.owner))
                .leftJoin(buildingAddress).on(buildingAddress.eq(building.buildingAddress))
                .leftJoin(addressLevel1).on(buildingAddress.addressLevel1Id.eq(addressLevel1.addressLevel1Id))
                .leftJoin(addressLevel2).on(buildingAddress.addressLevel2Id.eq(addressLevel2.addressLevel2Id))
                .where(
                        BooleanExpressionBuilder.orBooleanBuilder(
                                ownerPhoneNumberLike(buildingCondition.getOwnerPhoneNumber()),
                                buildingAddress1Eq(buildingCondition.getAddressLevel1Id()),
                                buildingAddress2Eq(buildingCondition.getAddressLevel2Id())
                        )
                )
                .fetch();
    }

    private BooleanExpression ownerPhoneNumberLike(String phoneNumber) {
        return hasText(phoneNumber) ? owner.ownerPhoneNumber.like('%' + phoneNumber + '%') : null;
    }

    private BooleanExpression buildingAddress1Eq(Long addressLevel1Id) {
        return addressLevel1Id != null ? buildingAddress.addressLevel1Id.eq(addressLevel1Id) : null;
    }

    private BooleanExpression buildingAddress2Eq(Long addressLevel2Id) {
        return addressLevel2Id != null ? buildingAddress.addressLevel2Id.eq(addressLevel2Id) : null;
    }
}
