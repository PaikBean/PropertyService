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
    private final EntityExceptionService entityExceptionService;

    /**
     * 임대인 중복 확인( api 미지정 )
     */
    private Owner validOwnerDuplicate(String phoneNumber) {
        return ownerRepository.findByOwnerPhoneNumber(phoneNumber).orElse(null);
    }

    /**
     * 주소 validation
     */
    private void validBuildingDuplicate(Long addressLevel1Id, Long addressLevel2Id, String addressLevel3) {
        if (buildingAddressRepository.existsByAddressLevel1IdAndAddressLevel2IdAndAddressLevel3(addressLevel1Id, addressLevel2Id, addressLevel3)) {
            throw new IllegalStateException("등록된 건물의 주소입니다.");
        }
    }

    /**
     * 주소1 validation
     */
    private Long validAddressLevel1(Long addressLevel1Id) {
        return entityExceptionService.findEntityById(
                () -> addressLevel1Repository.findById(addressLevel1Id),
                "주소 입력이 잘못되었습니다."
        ).getAddressLevel1Id();
    }
    /**
     * 주소2 validation
     */
    private Long validAddressLevel2(Long addressLevel2Id) {
        return entityExceptionService.findEntityById(
                () -> addressLevel2Respository.findById(addressLevel2Id),
                "주소 입력이 잘못되었습니다."
        ).getAddressLevel2Id();
    }

    /**
     * 건물 목록 조회.
     */
    public List<BuildingDto> searchBuildingList(BuildingCondition buildingCondition) {
        return buildingRepository.searchBuildingList(buildingCondition);
    }

    @Transactional
    public void createBuilding(BuildingOwnerForm buildingOwnerForm) {
        Owner isOwnerExsit = entityExceptionService.findEntityById(
                () -> ownerRepository.findByOwnerPhoneNumber(buildingOwnerForm.getOwnerPhoneNumber()),
                "임대인 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );

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


    //건물 상세 단건 조회 - 건물 관리
    public BuildingInfoDto searchBuildingInfo(Long buildingId){
        Building building = entityExceptionService.findEntityById(
                () -> buildingRepository.findById(buildingId),
                "빌딩 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );

        Owner owner = entityExceptionService.findEntityById(
                () -> ownerRepository.findById(building.getOwner().getOwnerId()),
                "임대인 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );

        BuildingAddress buildingAddress = entityExceptionService.findEntityById(
                () -> buildingAddressRepository.findById(building.getBuildingAddress().getBuildingAddressId()),
                "빌딩 주소 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );
        return BuildingInfoDto.builder()
                .buildingId(building.getBuildingId())
                .ownerName(owner.getOwnerName())
                .ownerRelation(owner.getOwnerRelation())
                .ownerPhoneNumber(owner.getOwnerPhoneNumber())
                .buildingAddressLevel1(buildingAddress.getAddressLevel1Id())
                .buildingAddressLevel2(buildingAddress.getAddressLevel2Id())
                .buildingAddressLevel3(buildingAddress.getAddressLevel3())
                .buildingRemarkList(searchBuildingRemarkList(building.getBuildingId()))
                .build();
    }

    /**
     * 건물 특이사항 추가.
     */
    @Transactional
    public Long createBuildingRemark(BuildingRemarkForm buildingRemarkForm) {
        return buildingRemarkRepository.save(BuildingRemark.builder()
                        .building(
                                entityExceptionService.findEntityById(
                                        () -> buildingRepository.findById(buildingRemarkForm.getBuildingId()),
                                        "빌딩 정보가 존재하지 않습니다. 관리자에게 문의하세요"
                                )
                        )
                        .remark(buildingRemarkForm.getRemark())
                        .build())
                .getBuilding().getBuildingId();
    }

    /**
     * 건물 특이사항 제거.
     * @param buildingRemarkId
     */
    @Transactional
    public void deleteBuildingRemark(Long buildingRemarkId) {
        BuildingRemark buildingRemark = entityExceptionService.findEntityById(
                () -> buildingRemarkRepository.findById(buildingRemarkId),
                "특이사항 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );
        buildingRemarkRepository.delete(buildingRemark);
    }


    /**
     * 건물 매물 몰고
     */
    public BuildingPropertyDto searchBuildingPropertyList(Long buildingId) {
        // 예외처리.
        Building building = entityExceptionService.findEntityById(
                () -> buildingRepository.findById(buildingId),
                "빌딩 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );

        //buildingPropertyList
        List<PropertySummaryDto> buildingPropertyList = new ArrayList<>();
        for (Property property : propertyRepository.findAllByBuildingBuildingId(buildingId)) {
            buildingPropertyList.add(
                    PropertySummaryDto.builder()
                            .propertyId(property.getPropertyId())
                            .unitNumber(property.getUnitNumber())
                            .propertyType(property.getPropertyType())
                            .transactionType(property.getTransactionType())
                            .price(getSummaryPrice(property))
                            .transactionState(property.getTransactionState())
                            .build()
            );
        }
        // BuildingRemarkList
        List<BuildingRemarkDto> buildingRemarkList = searchBuildingRemarkList(building.getBuildingId());

        return BuildingPropertyDto.builder()
                .buildingId(building.getBuildingId())
                .ownerName(building.getOwner().getOwnerName())
                .ownerPhoneNumber(building.getOwner().getOwnerPhoneNumber())
                .ownerRelation(building.getOwner().getOwnerRelation())
                .addressLevel1(building.getBuildingAddress().getAddressLevel1Id())
                .addressLevel2(building.getBuildingAddress().getAddressLevel2Id())
                .addressLevel3(building.getBuildingAddress().getAddressLevel3())
                .buildingRemarkList(buildingRemarkList)
                .buildingPropertyList(buildingPropertyList)
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

    /**
     * 건물 상세 정보 수정.
     */
    @Transactional
    public Long updateBuildingDetail(BuildingPropertyForm buildingPropertyForm) {
        validAddressLevel1(buildingPropertyForm.getAddressLevel1());
        validAddressLevel2(buildingPropertyForm.getAddressLevel2());

        Building building = entityExceptionService.findEntityById(
                () -> buildingRepository.findById(buildingPropertyForm.getBuildingId()),
                "빌딩 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );

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

    /**
     * 건물 특이사항 목록 조회.
     * @param buildingId
     * @return
     */
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
