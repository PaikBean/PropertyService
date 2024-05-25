package com.propertyservice.propertyservice.dto.building;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingOwnerForm {
    @NotNull @NotBlank
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    @NotNull
    private Long buildingAddressLevel1;
    @NotNull
    private Long buildingAddressLevel2;
    private String buildingAddressLevel3;
    private String buildingRemark;
}
