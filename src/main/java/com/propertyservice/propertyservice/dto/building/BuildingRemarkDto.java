package com.propertyservice.propertyservice.dto.building;

import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import lombok.Builder;

import java.time.LocalDateTime;

public class BuildingRemarkDto {
    private Long buildingRemarkId;
    private String remark;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public BuildingRemarkDto(BuildingRemark buildingRemark) {
        this.buildingRemarkId = buildingRemark.getRemarkId();
        this.remark = buildingRemark.getRemark();
        this.createdDate = buildingRemark.getCreatedDate();
        this.updatedDate = buildingRemark.getUpdatedDate();
    }
}
