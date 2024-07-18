package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.building.BuildingAddress;
import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import com.propertyservice.propertyservice.domain.building.Owner;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.dto.building.*;
import com.propertyservice.propertyservice.dto.property.PropertySummaryDto;
import com.propertyservice.propertyservice.repository.building.BuildingAddressRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRemarkRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.building.OwnerRepository;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
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
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingRemarkRepository buildingRemarkRepository;
    private final BuildingAddressRepository buildingAddressRepository;
    private final OwnerRepository ownerRepository;
    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Respository addressLevel2Respository;
    private final PropertyRepository propertyRepository;

    public List<BuildingDto> searchBuildingList(BuildingCondition buildingCondition) {
        return buildingRepository.searchBuildingList(buildingCondition);
    }

    @Transactional
    public void createBuilding(BuildingOwnerForm buildingOwnerForm) {
        Owner isOwnerExsit = validOwnerDuplicate(buildingOwnerForm.getOwnerPhoneNumber());
        Owner owner = isOwnerExsit == null ?
                ownerRepository.save(Owner.builder()
                        .ownerName(buildingOwnerForm.getOwnerName())
                        .ownerRelation(buildingOwnerForm.getOwnerRelation())
                        .ownerPhoneNumber(buildingOwnerForm.getOwnerPhoneNumber())
                        .build())
                : isOwnerExsit;
        validBuildingDuplicate(buildingOwnerForm.getBuildingAddressLevel1(), buildingOwnerForm.getBuildingAddressLevel2(), buildingOwnerForm.getBuildingAddressLevel3());
        BuildingAddress buildingAddress = buildingAddressRepository.save(BuildingAddress.builder()
                .addressLevel1Id(validAddressLevel1(buildingOwnerForm.getBuildingAddressLevel1()))
                .addressLevel2Id(validAddressLevel2(buildingOwnerForm.getBuildingAddressLevel2()))
                .addressLevel3(buildingOwnerForm.getBuildingAddressLevel3())
                .build());
        Building building = buildingRepository.save(Building.builder()
                .owner(owner)
                .buildingAddress(buildingAddress)
                .build());
        buildingRemarkRepository.save(BuildingRemark.builder()
                .building(building)
                .remark(buildingOwnerForm.getBuildingRemark())
                .build());
    }

    private Long validAddressLevel1(Long addressLevel1Id) {
        return addressLevel1Repository.findById(addressLevel1Id).orElseThrow(
                () -> new IllegalStateException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel1Id();
    }

    private Long validAddressLevel2(Long addressLevel2Id) {
        return addressLevel2Respository.findById(addressLevel2Id).orElseThrow(
                () -> new IllegalStateException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel2Id();
    }

    private Owner validOwnerDuplicate(String phoneNumber) {
        return ownerRepository.findByOwnerPhoneNumber(phoneNumber).orElse(null);
    }

    private void validBuildingDuplicate(Long addressLevel1Id, Long addressLevel2Id, String addressLevel3) {
        if (buildingAddressRepository.existsByAddressLevel1IdAndAddressLevel2IdAndAddressLevel3(addressLevel1Id, addressLevel2Id, addressLevel3)) {
            throw new IllegalStateException("등록된 건물의 주소입니다.");
        }
    }

    @Transactional
    public Long createBuildingRemark(BuildingRemarkForm buildingRemarkForm) {
        return buildingRemarkRepository.save(BuildingRemark.builder()
                        .building(
                                buildingRepository.findById(buildingRemarkForm.getBuildingId()).orElseThrow(
                                        () -> new EntityNotFoundException("등록되지 않은 빌딩입니다."))
                        )
                        .remark(buildingRemarkForm.getRemark())
                        .build())
                .getBuilding().getBuildingId();
    }

    @Transactional
    public void deleteBuildingRemark(BuildingRemarkIdForm buildingRemarkIdForm) {
        buildingRemarkRepository.deleteById(buildingRemarkIdForm.getBuildingRemarkId());
    }

    public BuildingPropertyDto searchBuildingPropertyList(Long buildingId) {
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
                            .transactionType(property.getTransactionType())
                            .price(getSummaryPrice(property))
                            .transactionState(property.getTransactionState())
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
        if (property.getTransactionType() == TransactionType.MONTHLY || property.getTransactionType() == TransactionType.SHORTERM)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getDeposit(), property.getMonthlyFee());
        else if (property.getTransactionType() == TransactionType.JEONSE)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getJeonseFee());
        else if (property.getTransactionType() == TransactionType.TRADE)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getTradeFee());
        else
            return null;
    }

    @Transactional
    public Long updateBuildingDetail(BuildingPropertyForm buildingPropertyForm) {
        validAddressLevel1(buildingPropertyForm.getAddressLevel1());
        validAddressLevel2(buildingPropertyForm.getAddressLevel2());
        Building building = buildingRepository.findById(buildingPropertyForm.getBuildingId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 빌딩입니다."));
        Owner owner = building.getOwner();
        BuildingAddress buildingAddress = building.getBuildingAddress();

        owner.updateOwner(
                buildingPropertyForm.getOwnerName(),
                buildingPropertyForm.getOwnerRelation(),
                buildingPropertyForm.getOwnerPhoneNumber()
        );
        buildingAddress.updateBuildingAddress(
                buildingPropertyForm.getAddressLevel1(),
                buildingPropertyForm.getAddressLevel2(),
                buildingPropertyForm.getAddressLevel3()
        );
        building.updateBuilding(
                owner,
                buildingAddress
        );

        buildingRepository.save(building);

        return building.getBuildingId();
    }

    public List<BuildingRemarkDto> searchBuildingRemarkList(Long buildingId) {
        List<BuildingRemarkDto> buildingRemarkDtoList = new ArrayList<>();
        for (BuildingRemark buildingRemark : buildingRemarkRepository.findAllByBuildingBuildingId(buildingId)) {
            buildingRemarkDtoList.add(BuildingRemarkDto.builder()
                            .buildingRemarkId(buildingRemark.getRemarkId())
                            .remark(buildingRemark.getRemark())
                            .createdDate(buildingRemark.getCreatedDate())
                            .updatedDate(buildingRemark.getUpdatedDate())
                    .build());
        }
        return buildingRemarkDtoList;
    }
}
