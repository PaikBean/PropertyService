package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.building.BuildingAddress;
import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import com.propertyservice.propertyservice.domain.building.Owner;
import com.propertyservice.propertyservice.dto.building.BuildingCondition;
import com.propertyservice.propertyservice.dto.building.BuildingDto;
import com.propertyservice.propertyservice.dto.building.BuildingOwnerForm;
import com.propertyservice.propertyservice.repository.building.BuildingAddressRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRemarkRepository;
import com.propertyservice.propertyservice.repository.building.BuildingRepository;
import com.propertyservice.propertyservice.repository.building.OwnerRepository;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
