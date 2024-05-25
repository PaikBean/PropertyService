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

    public void createBuilding(BuildingOwnerForm buildingOwnerForm) {
        Owner owner = ownerRepository.save(Owner.builder()
                .ownerName(buildingOwnerForm.getOwnerName())
                .ownerRelation(buildingOwnerForm.getOwnerRelation())
                .ownerPhoneNumber(buildingOwnerForm.getOwnerPhoneNumber())
                .build());
        BuildingAddress buildingAddress = buildingAddressRepository.save(BuildingAddress.builder()
                .addressLevel1Id(valiDaddressLevel1(buildingOwnerForm.getBuildingAddressLevel1()))
                .addressLevel2Id(valiDaddressLevel2(buildingOwnerForm.getBuildingAddressLevel2()))
                .addressLevel3(buildingOwnerForm.getBuildingAddressLevel3())
                .build());
        buildingRepository.save(Building.builder()
                .owner(owner)
                .buildingAddress(buildingAddress)
                .build());
        buildingRemarkRepository.save(BuildingRemark.builder()
                .remark(buildingOwnerForm.getBuildingRemark())
                .build());
    }

    private Long valiDaddressLevel1(Long addressLevel1Id){
        return addressLevel1Repository.findById(addressLevel1Id).orElseThrow(EntityNotFoundException::new).getAddressLevel1Id();
    }
    private Long valiDaddressLevel2(Long addressLevel2Id){
        return addressLevel2Respository.findById(addressLevel2Id).orElseThrow(EntityNotFoundException::new).getAddressLevel2Id();
    }
}
