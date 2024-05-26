package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import com.propertyservice.propertyservice.domain.property.MaintenanceItem;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.domain.property.PropertyRemark;
import com.propertyservice.propertyservice.dto.building.BuildingDto;
import com.propertyservice.propertyservice.dto.building.BuildingPropertyDto;
import com.propertyservice.propertyservice.dto.building.BuildingRemarkDto;
import com.propertyservice.propertyservice.dto.property.PropertyDto;
import com.propertyservice.propertyservice.dto.property.PropertyForm;
import com.propertyservice.propertyservice.dto.property.PropertyRemarkDto;
import com.propertyservice.propertyservice.dto.property.PropertySummaryDto;
import com.propertyservice.propertyservice.repository.building.BuildingRemarkRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.common.TransactionTypeRepository;
import com.propertyservice.propertyservice.repository.property.MaintenanceItemRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRemarkRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.repository.property.PropertyTypeRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
import com.sun.security.auth.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyService {
    private final BuildingRepository buildingRepository;
    private final PropertyRepository propertyRepository;
    private final MaintenanceItemRepository maintenanceItemRepository;
    private final PropertyRemarkRepository propertyRemarkRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final BuildingRemarkRepository buildingRemarkRepository;

    @Transactional
    public void createProperty(PropertyForm propertyForm) {
        Building building = buildingRepository.findById(propertyForm.getBuildingId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 빌딩입니다.")
        );

        validPropertyDuplicate(building, propertyForm.getUnitNumber());
        Property property = propertyRepository.save(
                Property.builder()
                        .building(building)
                        .unitNumber(propertyForm.getUnitNumber())
                        .picManagerId(propertyForm.getPicManagerId())
                        .propertyTypeId(propertyForm.getPropertyTypeId())
                        .transactionTypeId(propertyForm.getTransactionTypeId())
                        .deposit(propertyForm.getDeposit())
                        .monthlyFee(propertyForm.getMonthlyFee())
                        .jeonseFee(propertyForm.getJeonseFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .maintenanceFee(propertyForm.getMaintenanceFee())
                        .maintenanceItem(createMaintenanceItem(propertyForm))
                        .transactionStateId(propertyForm.getTransactionStateId())
                        .build()
        );
        System.out.println("propertyForm = " + propertyForm.getTransactionTypeId());
        propertyRemarkRepository.save(
                PropertyRemark.builder()
                        .property(property)
                        .remark(propertyForm.getRemark())
                        .build()
        );
    }

    private MaintenanceItem createMaintenanceItem(PropertyForm propertyForm) {
        return maintenanceItemRepository.save(MaintenanceItem.builder()
                .water(propertyForm.isMaintenanceItemWater())
                .electricity(propertyForm.isMaintenanceItemElectricity())
                .internet(propertyForm.isMaintenanceItemInternet())
                .gas(propertyForm.isMaintenanceItemGas())
                .others(propertyForm.getMaintenanceItemOthers())
                .build());
    }

    private void validPropertyDuplicate(Building building, String unitNumber) {
        if (propertyRepository.existsByBuildingAndUnitNumber(building, unitNumber)) {
            throw new IllegalStateException("이미 등록된 매물입니다.");
        }
    }

    public BuildingPropertyDto searchPropertyList(Long buildingId) {
        Building building = buildingRepository.findById(buildingId).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 빌딩입니다."));
        List<PropertySummaryDto> propertySummaryDtoList = new ArrayList<>();
        for (Property property : propertyRepository.findAllByBuildingBuildingId(buildingId)) {
            propertySummaryDtoList.add(
                    PropertySummaryDto.builder()
                            .propertyId(property.getPropertyId())
                            .propertyTypeId(property.getPropertyTypeId())
                            .unitNumber(property.getUnitNumber())
                            .propertyTypeId(property.getPropertyTypeId())
                            .transactionTypeId(property.getTransactionTypeId())
                            .price(getSummaryPrice(property))
                            .transactionStateId(property.getTransactionStateId())
                            .transactionStateId(property.getTransactionStateId())
                            .build()
            );
        }
        List<BuildingRemarkDto> buildingRemarkDtoList = new ArrayList<>();
        for (BuildingRemark buildingRemark : buildingRemarkRepository.findAllByBuildingBuildingId(buildingId)) {
            buildingRemarkDtoList.add(BuildingRemarkDto.builder()
                    .buildingRemarkId(buildingRemark.getRemarkId())
                    .remark(buildingRemark.getRemark())
                    .createdDate(buildingRemark.getCreatedDate())
                    .updatedDate(buildingRemark.getUpdatedDate())
                    .build());
        }

        return BuildingPropertyDto.builder()
                .buildingId(building.getBuildingId())
                .ownerName(building.getOwner().getOwnerName())
                .ownerPhoneNumber(building.getOwner().getOwnerPhoneNumber())
                .ownerRelation(building.getOwner().getOwnerRelation())
                .addressLevel1(building.getBuildingAddress().getAddressLevel1Id())
                .addressLevel2(building.getBuildingAddress().getAddressLevel2Id())
                .addressLevel3(building.getBuildingAddress().getAddressLevel3())
                .buildingRemarkDtoList(buildingRemarkDtoList)
                .propertySummaryDtoList(propertySummaryDtoList)
                .build();
    }

    private String getSummaryPrice(Property property) {
        if (property.getTransactionTypeId() == 1 || property.getTransactionTypeId() == 4)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getDeposit(), property.getMonthlyFee());
        else if (property.getTransactionTypeId() == 2)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getJeonseFee());
        else if (property.getTransactionTypeId() == 3)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getTradeFee());
        else
            return null;
    }

    public PropertyDto searchProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매물입니다.")
        );

        List<PropertyRemarkDto> propertyRemarkDtoList = new ArrayList<>();
        for (PropertyRemark propertyRemark : propertyRemarkRepository.findByPropertyPropertyId(propertyId)) {
            propertyRemarkDtoList.add(PropertyRemarkDto.builder()
                    .propertyRemarkId(propertyRemark.getRemarkId())
                    .propertyRemark(propertyRemark.getRemark())
                    .createdDate(propertyRemark.getCreatedDate())
                    .updatedDate(propertyRemark.getUpdatedDate())
                    .build());
        }

        return PropertyDto.builder()
                .propertyId(property.getPropertyId())
                .unitNumber(property.getUnitNumber())
                .picManagerId(property.getPicManagerId())
                .picManagerName(null)   // Todo 추후 manager 엔티티 주가되면 가져오기
                .transactionTypeId(property.getTransactionTypeId())
                .deposit(property.getDeposit())
                .monthlyFee(property.getMonthlyFee())
                .jeonseFee(property.getJeonseFee())
                .tradeFee(property.getTradeFee())
                .maintenanceFee(property.getMaintenanceFee())
                .maintenanceItemWater(property.getMaintenanceItem().isWater())
                .maintenanceItemElectricity(property.getMaintenanceItem().isElectricity())
                .maintenanceItemInternet(property.getMaintenanceItem().isInternet())
                .maintenanceItemGas(property.getMaintenanceItem().isGas())
                .maintenanceItemOthers(property.getMaintenanceItem().getOthers())
                .transactionStateId(property.getTransactionStateId())
                .propertyRemarkDtoList(propertyRemarkDtoList)
                .build();
    }
}
