package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.property.MaintenanceItem;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.domain.property.PropertyRemark;
import com.propertyservice.propertyservice.dto.property.PropertyForm;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.property.MaintenanceItemRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRemarkRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.sun.security.auth.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyService {
    private final BuildingRepository buildingRepository;
    private final PropertyRepository propertyRepository;
    private final MaintenanceItemRepository maintenanceItemRepository;
    private final PropertyRemarkRepository propertyRemarkRepository;

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
                        .transactionTypeId(propertyForm.getTransactionTypeId())
                        .deposit(propertyForm.getDeposit())
                        .monthlyFee(propertyForm.getMonthlyFee())
                        .jeonseFee(propertyForm.getJeonseFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .maintenanceFee(propertyForm.getMaintenanceFee())
                        .maintenanceItem(createMaintenanceItem(propertyForm))
                        .build()
        );
        propertyRemarkRepository.save(
                PropertyRemark.builder()
                        .property(property)
                        .remark(propertyForm.getRemark())
                        .build()
        );
    }

    private MaintenanceItem createMaintenanceItem(PropertyForm propertyForm){
        return maintenanceItemRepository.save(MaintenanceItem.builder()
                .water(propertyForm.isMaintenanceItemWater())
                .electricity(propertyForm.isMaintenanceItemElectricity())
                .internet(propertyForm.isMaintenanceItemInternet())
                .gas(propertyForm.isMaintenanceItemGas())
                .others(propertyForm.getMaintenanceItemOthers())
                .build());
    }

    private void validPropertyDuplicate(Building building, String unitNumber){
        if (propertyRepository.existsByBuildingAndUnitNumber(building, unitNumber)) {
            throw new IllegalStateException("이미 등록된 매물입니다.");
        }
    }
}
