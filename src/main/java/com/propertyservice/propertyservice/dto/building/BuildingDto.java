package com.propertyservice.propertyservice.dto.building;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.repository.Query;

@Getter
public class BuildingDto {
    private Long buildingId;
    private String ownerName;
    private String ownerRelation;
    private String ownerPhoneNumber;
    private String buildingAddress;

    @Builder
    @QueryProjection
    public BuildingDto(Long buildingId, String ownerName, String ownerRelation, String ownerPhoneNumber, String buildingAddress) {
        this.buildingId = buildingId;
        this.ownerName = ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.buildingAddress = buildingAddress;
    }

    @QueryProjection
    public BuildingDto(Long buildingId, String ownerName, String buildingAddress) {
        this.buildingId = buildingId;
        this.ownerName = ownerName;
        this.buildingAddress = buildingAddress;
    }
}
