package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.domain.property.*;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyForm;
import com.propertyservice.propertyservice.dto.property.*;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.client.ClientRepository;
import com.propertyservice.propertyservice.repository.client.ShowingPropertyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
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
    private final PropertyImageRepository propertyImageRepository;
    private final ShowingPropertyRepository showingPropertyRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    private final CommonService commonService;
    private final EntityExceptionService entityExceptionService;
    private final CustomUserDetailService customUserDetailService;

    /**
     * 보여줄 매물 추가.
     */
    public Long createShowingProrperty(ShowingPropertyForm showingPropertyForm){
        Manager manager = commonService.getCustomUserDetailBySecurityContextHolder().getManager();

        return showingPropertyRepository.save(ShowingProperty.builder()
                .clientId(
                        entityExceptionService.findEntityById(
                        () -> clientRepository.findById(showingPropertyForm.getClientId()),
                        "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getClientId()
                )
                .propertyId(entityExceptionService.findEntityById(
                        () -> propertyRepository.findById(showingPropertyForm.getPropertyId()),
                        "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getPropertyId()
                )
                .registrationManagerId(entityExceptionService.findEntityById(
                        () -> managerRepository.findById(manager.getManagerId()),
                        "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                )
                .modifiedManagerId(entityExceptionService.findEntityById(
                        () -> managerRepository.findById(manager.getManagerId()),
                        "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                )
                .build()).getClientId();
    }

    /**
     * 보여줄 매물 삭제.
     */
    @Transactional
    public void deleteShowingProperty(Long showingPropertyId){
        showingPropertyRepository.delete( entityExceptionService.findEntityById(
                () -> showingPropertyRepository.findById(showingPropertyId),
                "보여줄 매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
            )
        );
    }

    @Transactional
    public void createProperty(PropertyForm propertyForm) {
        Building building = entityExceptionService.findEntityById(
                () -> buildingRepository.findById(propertyForm.getBuildingId()),
                "건물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
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
//                        .maintenanceFee(propertyForm.getMaintenanceFee())
//                        .maintenanceItem(createMaintenanceItem(propertyForm))
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
        Property property = entityExceptionService.findEntityById(
                () -> propertyRepository.findById(propertyId),
                "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
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
                .picManagerName(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findById(property.getPropertyId()),
                                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerName())
                .propertyType(property.getPropertyType())
                .transactionType(property.getTransactionType())
                .deposit(property.getDeposit())
                .monthlyFee(property.getMonthlyFee())
                .jeonseFee(property.getJeonseFee())
                .tradeFee(property.getTradeFee())
                .shortTermDeposit(property.getShortTermDeposit())
                .shortTermMonthlyFee(property.getShortTermMonthlyFee())
//                .maintenanceFee(property.getMaintenanceFee())
//                .maintenanceItemWater(property.getMaintenanceItem().isWater())
//                .maintenanceItemElectricity(property.getMaintenanceItem().isElectricity())
//                .maintenanceItemInternet(property.getMaintenanceItem().isInternet())
//                .maintenanceItemGas(property.getMaintenanceItem().isGas())
//                .maintenanceItemOthers(property.getMaintenanceItem().getOthers())
                .transactionState(property.getTransactionState())
                //.propertyRemarkDtoList(propertyRemarkDtoList)
                .commision(property.getCommision())
                .remark(property.getRemark())
                .build();
    }

    @Transactional
    public void updateProperty(PropertyForm propertyForm) {
        Property property = entityExceptionService.findEntityById(
                () -> propertyRepository.findById(propertyForm.getPropertyId()),
                "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );

//        //관리비 업데이트.
//        MaintenanceItem maintenanceItem = property.getMaintenanceItem();
//        maintenanceItem.updateMaintenanceItem(
//                propertyForm.isMaintenanceItemWater(),
//                propertyForm.isMaintenanceItemElectricity(),
//                propertyForm.isMaintenanceItemInternet(),
//                propertyForm.isMaintenanceItemGas(),
//                propertyForm.getMaintenanceItemOthers()
//        );

        property.updateProperty(
                entityExceptionService.findEntityById(
                        () -> buildingRepository.findById(property.getPropertyId()),
                        "건물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
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
//                maintenanceItem,
                null,
                propertyForm.getTransactionState(),
                propertyForm.getCommision(),
                propertyForm.getRemark()
        );
    }

    @Transactional
    public void deleteProperty(Long propertyId) {
        Property property = entityExceptionService.findEntityById(
                () -> propertyRepository.findById(propertyId),
                "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );

//        maintenanceItemRepository.delete(property.getMaintenanceItem());

        //propertyRemarkRepository.deleteAllByProperty(property);
        propertyImageRepository.deleteAllByProperty(property);
        propertyRepository.delete(property);
    }

    public List<PropertyTypeDto> searchPropertyTypeList() {
        return Arrays.stream(PropertyType.values())
                .map(propertyType -> new PropertyTypeDto(propertyType.name(), propertyType.getLabel()))
                .collect(Collectors.toList());
    }

    public void createShowingProrperties(ShowingPropertyForm showingPropertyForm) {
        Manager manager = commonService.getCustomUserDetailBySecurityContextHolder().getManager();

        log.info("사이즈! : {}",showingPropertyForm.getShowingPropertyList().size());
        for (Long propertyId : showingPropertyForm.getShowingPropertyList()) {
            log.info("타겟! : {}",propertyId);
            showingPropertyRepository.save(ShowingProperty.builder()
                    .propertyId(entityExceptionService.findEntityById(
                            () -> propertyRepository.findById(propertyId),
                            "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getPropertyId()
                    )
                    .clientId(
                            entityExceptionService.findEntityById(
                                    () -> clientRepository.findById(showingPropertyForm.getClientId()),
                                    "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getClientId()
                    )
                    .registrationManagerId(entityExceptionService.findEntityById(
                            () -> managerRepository.findById(manager.getManagerId()),
                            "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                    )
                    .modifiedManagerId(entityExceptionService.findEntityById(
                            () -> managerRepository.findById(manager.getManagerId()),
                            "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                    )
                    .build());
        }

    }
}
