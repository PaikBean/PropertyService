package com.propertyservice.propertyservice.dto.building;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingInfoDto {
    private Long buildingId;
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    private Long buildingAddressLevel1;
    private Long buildingAddressLevel2;
    private String buildingAddressLevel3;
//    private String buildingRemark;
    List<BuildingRemarkDto> buildingRemarkList;


    @Builder
    public BuildingInfoDto(Long buildingId, String ownerName, String ownerRelation, String ownerPhoneNumber, Long buildingAddressLevel1, Long buildingAddressLevel2, String buildingAddressLevel3, List<BuildingRemarkDto> buildingRemarkList){
        this.buildingId =buildingId;
        this.ownerName =ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.buildingAddressLevel1 = buildingAddressLevel1;
        this.buildingAddressLevel2 = buildingAddressLevel2;
        this.buildingAddressLevel3 =buildingAddressLevel3;
       // this.buildingRemark = buildingRemark;
        this.buildingRemarkList =buildingRemarkList;
    }

}
