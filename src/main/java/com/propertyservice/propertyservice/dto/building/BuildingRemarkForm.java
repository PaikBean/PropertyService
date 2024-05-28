package com.propertyservice.propertyservice.dto.building;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuildingRemarkForm {
    @NotNull
    private Long buildingId;
    @NotNull
    private String remark;
}
