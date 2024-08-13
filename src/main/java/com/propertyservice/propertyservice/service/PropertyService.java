package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.domain.property.*;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyForm;
import com.propertyservice.propertyservice.dto.property.*;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.client.ClientRepository;
import com.propertyservice.propertyservice.repository.client.ShowingPropertyRepository;
import com.propertyservice.propertyservice.repository.property.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyService {
    private final BuildingRepository buildingRepository;
    private final PropertyRepository propertyRepository;
    private final MaintenanceItemRepository maintenanceItemRepository;
    private final PropertyRemarkRepository propertyRemarkRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final ShowingPropertyRepository showingPropertyRepository;
    private final ClientRepository clientRepository;
    private final ManagerService managerService;
    private final CommonService commonService;

    /**
     * 보여줄 매물 추가.
     */
    public Long createShowingProrperty(ShowingPropertyForm showingPropertyForm){
        Manager manager = commonService.getCustomUserDetailBySecurityContextHolder().getManager();

        return showingPropertyRepository.save(ShowingProperty.builder()
                .clientId(clientRepository.findById(showingPropertyForm.getClientId()).orElseThrow(
                                () -> new EntityNotFoundException("고객 정보를 찾을수 없습니다.")
                        ).getClientId()
                )
                .propertyId(propertyRepository.findById(showingPropertyForm.getClientId()).orElseThrow(
                                () -> new EntityNotFoundException("매물 정보를 찾을 수 없습니다.")
                        ).getPropertyId()
                )
                .registrationManagerId(managerService.searchManagerIdById(manager.getManagerId()))
                .modifiedManagerId(managerService.searchManagerIdById(manager.getManagerId()))
                .build()).getClientId();
    }

    /**
     * 보여줄 매물 삭제.
     */
    public void deleteShowingProperty(Long showingPropertyId){
        ShowingProperty showingProperty = showingPropertyRepository.findById(showingPropertyId).orElseThrow(
                () -> new EntityNotFoundException("보여줄 매물 정보를 찾을 수 없습니다.")
        );
        showingPropertyRepository.delete(showingProperty);
    }

    @Transactional
    public void createProperty(PropertyForm propertyForm) {
        Building building = buildingRepository.findById(propertyForm.getBuildingId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 빌딩입니다.")
        );
        validPropertyDuplicate(building, propertyForm.getUnitNumber());

       propertyRepository.save(
                Property.builder()
                        .building(building)
                        .picManagerId(propertyForm.getPicManagerId())
                        .propertyType(propertyForm.getPropertyType())
                        .transactionType(propertyForm.getTransactionType())
                        .unitNumber(propertyForm.getUnitNumber())
                        .deposit(propertyForm.getDeposit())
                        .monthlyFee(propertyForm.getMonthlyFee())
                        .jeonseFee(propertyForm.getJeonseFee())
                        .tradeFee(propertyForm.getTradeFee())
                        .shortTermDeposit(propertyForm.getShortTermDeposit())
                        .shortTermMonthlyFee(propertyForm.getShortTermMonthlyFee())
                        .maintenanceFee(propertyForm.getMaintenanceFee())
                        .maintenanceItem(createMaintenanceItem(propertyForm))
                        .transactionState(propertyForm.getTransactionState())
                        .commision(propertyForm.getCommision())
                        .remark(propertyForm.getRemark())
                        .build()
        );
//        if(propertyForm.getRemark() != null){
//            propertyRemarkRepository.save(
//                    PropertyRemark.builder()
//                            .property(property)
//                            .remark(propertyForm.getRemark())
//                            .build()
//            );
//        }
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

//        List<PropertyRemarkDto> propertyRemarkDtoList = new ArrayList<>();
//        for (PropertyRemark propertyRemark : propertyRemarkRepository.findByPropertyPropertyId(propertyId)) {
//            propertyRemarkDtoList.add(PropertyRemarkDto.builder()
//                    .propertyRemarkId(propertyRemark.getRemarkId())
//                    .propertyRemark(propertyRemark.getRemark())
//                    .createdDate(propertyRemark.getCreatedDate())
//                    .updatedDate(propertyRemark.getUpdatedDate())
//                    .build());
//        }


        return PropertyDto.builder()
                .propertyId(property.getPropertyId())
                .unitNumber(property.getUnitNumber())
                .picManagerId(property.getPicManagerId())
                .picManagerName(managerService.searchManagerById(property.getPicManagerId()).getManagerName())   // Todo 추후 manager 엔티티 주가되면 가져오기
                .propertyType(property.getPropertyType())
                .transactionType(property.getTransactionType())
                .deposit(property.getDeposit())
                .monthlyFee(property.getMonthlyFee())
                .jeonseFee(property.getJeonseFee())
                .tradeFee(property.getTradeFee())
                .shortTermDeposit(property.getShortTermDeposit())
                .shortTermMonthlyFee(property.getShortTermMonthlyFee())
                .maintenanceFee(property.getMaintenanceFee())
                .maintenanceItemWater(property.getMaintenanceItem().isWater())
                .maintenanceItemElectricity(property.getMaintenanceItem().isElectricity())
                .maintenanceItemInternet(property.getMaintenanceItem().isInternet())
                .maintenanceItemGas(property.getMaintenanceItem().isGas())
                .maintenanceItemOthers(property.getMaintenanceItem().getOthers())
                .transactionState(property.getTransactionState())
                //.propertyRemarkDtoList(propertyRemarkDtoList)
                .commision(property.getCommision())
                .remark(property.getRemark())
                .build();
    }

    @Transactional
    public void updateProperty(PropertyForm propertyForm) {
        Property property = propertyRepository.findById(propertyForm.getPropertyId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매물입니다."));

        //관리비 업데이트.
        MaintenanceItem maintenanceItem = property.getMaintenanceItem();
        maintenanceItem.updateMaintenanceItem(
                propertyForm.isMaintenanceItemWater(),
                propertyForm.isMaintenanceItemElectricity(),
                propertyForm.isMaintenanceItemInternet(),
                propertyForm.isMaintenanceItemGas(),
                propertyForm.getMaintenanceItemOthers()
        );

        property.updateProperty(
                buildingRepository.findById(propertyForm.getBuildingId()).orElseThrow(
                        () -> new EntityNotFoundException("등록되지 않은 빌딩입니다.")
                ),
                propertyForm.getUnitNumber(),
                propertyForm.getPicManagerId(),
                propertyForm.getPropertyType(),
                propertyForm.getTransactionType(),
                propertyForm.getDeposit(),
                propertyForm.getMonthlyFee(),
                propertyForm.getJeonseFee(),
                propertyForm.getTradeFee(),
                propertyForm.getShortTermDeposit(),
                propertyForm.getShortTermMonthlyFee(),
                propertyForm.getMaintenanceFee(),
                maintenanceItem,
                propertyForm.getTransactionState(),
                propertyForm.getCommision(),
                propertyForm.getRemark()
        );
    }

    @Transactional
    public void deleteProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매물입니다."));
        maintenanceItemRepository.delete(property.getMaintenanceItem());
        //propertyRemarkRepository.deleteAllByProperty(property);
        propertyImageRepository.deleteAllByProperty(property);
        propertyRepository.delete(property);
    }

    public List<PropertyTypeDto> searchPropertyTypeList() {
        return Arrays.stream(PropertyType.values())
                .map(propertyType -> new PropertyTypeDto(propertyType.name(), propertyType.getLabel()))
                .collect(Collectors.toList());
    }
}
