package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.property.MaintenanceItem;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.domain.property.PropertyRemark;
import com.propertyservice.propertyservice.dto.property.*;
import com.propertyservice.propertyservice.repository.building.BuildingRemarkRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.property.*;
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
    private final BuildingRemarkRepository buildingRemarkRepository;
    private final PropertyImageRepository propertyImageRepository;

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
                        .transactionType(propertyForm.getTransactionType())
                        .deposit(propertyForm.getDeposit())
                        .monthlyFee(propertyForm.getMonthlyFee())
                        .jeonseFee(propertyForm.getJeonseFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .maintenanceFee(propertyForm.getMaintenanceFee())
                        .maintenanceItem(createMaintenanceItem(propertyForm))
                        .transactionState(propertyForm.getTransactionState())
                        .build()
        );
        System.out.println("propertyForm = " + propertyForm.getTransactionType().toString());
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
                .transactionType(property.getTransactionType())
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
                .transactionState(property.getTransactionState())
                .propertyRemarkDtoList(propertyRemarkDtoList)
                .build();
    }

    @Transactional
    public Long updateProperty(PropertyForm propertyForm) {
        Property property = propertyRepository.findById(propertyForm.getPropertyId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매물입니다."));
        MaintenanceItem maintenanceItem = property.getMaintenanceItem();
        maintenanceItem.updateMaintenanceItem(
                propertyForm.isMaintenanceItemWater(),
                propertyForm.isMaintenanceItemElectricity(),
                propertyForm.isMaintenanceItemInternet(),
                propertyForm.isMaintenanceItemGas(),
                propertyForm.getMaintenanceItemOthers()
        );
        property.updateProperty(
                propertyForm.getUnitNumber(),
                propertyForm.getPicManagerId(),
                propertyForm.getPropertyTypeId(),
                propertyForm.getTransactionType(),
                propertyForm.getDeposit(),
                propertyForm.getMonthlyFee(),
                propertyForm.getJeonseFee(),
                propertyForm.getTradeFee(),
                propertyForm.getMaintenanceFee(),
                maintenanceItem,
                propertyForm.getTransactionState()
        );
        return property.getPropertyId();
    }

    @Transactional
    public void deleteProperty(PropertyIdForm propertyIdForm) {
        Property property = propertyRepository.findById(propertyIdForm.getPropertyId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매물입니다."));
        maintenanceItemRepository.delete(property.getMaintenanceItem());
        propertyRemarkRepository.deleteAllByProperty(property);
        propertyImageRepository.deleteAllByProperty(property);
        propertyRepository.delete(property);
    }
}
