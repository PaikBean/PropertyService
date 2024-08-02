package com.propertyservice.propertyservice.dto.building;

import com.propertyservice.propertyservice.dto.property.PropertySummaryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BuildingPropertyDto {
    private Long buildingId;
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    private Long addressLevel1;
    private Long addressLevel2;
    private String addressLevel3;

    private List<BuildingRemarkDto> buildingRemarkList;

    private List<PropertySummaryDto> buildingPropertyList;

    @Builder
    public BuildingPropertyDto(Long buildingId, String ownerName, String ownerRelation, String ownerPhoneNumber, Long addressLevel1, Long addressLevel2, String addressLevel3, List<BuildingRemarkDto> buildingRemarkList, List<PropertySummaryDto> buildingPropertyList) {
        this.buildingId = buildingId;
        this.ownerName = ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressLevel3 = addressLevel3;
        this.buildingRemarkList = buildingRemarkList;
        this.buildingPropertyList = buildingPropertyList;
    }
}
