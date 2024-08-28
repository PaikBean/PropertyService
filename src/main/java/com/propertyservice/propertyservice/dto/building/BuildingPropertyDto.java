package com.propertyservice.propertyservice.dto.building;

import com.propertyservice.propertyservice.dto.property.PropertySummaryDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BuildingPropertyDto {
    private Long buildingId;
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    private String buildingAddress;

    private List<BuildingRemarkDto> buildingRemarkList;

    private List<PropertySummaryDto> buildingPropertyList;

    @Builder
    public BuildingPropertyDto(Long buildingId, String ownerName, String ownerRelation, String ownerPhoneNumber, String buildingAddress, List<BuildingRemarkDto> buildingRemarkList, List<PropertySummaryDto> buildingPropertyList) {
        this.buildingId = buildingId;
        this.ownerName = ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.buildingAddress =buildingAddress;
        this.buildingRemarkList = buildingRemarkList;
        this.buildingPropertyList = buildingPropertyList;
    }
}
