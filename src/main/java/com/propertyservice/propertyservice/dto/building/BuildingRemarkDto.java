package com.propertyservice.propertyservice.dto.building;

import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BuildingRemarkDto {
    private Long buildingRemarkId;
    private String remark;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public BuildingRemarkDto(Long buildingRemarkId, String remark, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.buildingRemarkId = buildingRemarkId;
        this.remark = remark;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
