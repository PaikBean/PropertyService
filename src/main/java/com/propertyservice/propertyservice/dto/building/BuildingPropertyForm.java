package com.propertyservice.propertyservice.dto.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingPropertyForm {
    private Long buildingId;
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    private Long addressLevel1;
    private Long addressLevel2;
    private String addressLevel3;
}
