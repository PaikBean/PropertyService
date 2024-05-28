package com.propertyservice.propertyservice.domain.building;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "building_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingAddress extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_address_id")
    private Long buildingAddressId;
    @Column(name = "address_level1_id", nullable = false)
    private Long addressLevel1Id;
    @Column(name = "address_level2_id", nullable = false)
    private Long addressLevel2Id;
    private String addressLevel3;

    @Builder
    public BuildingAddress(Long buildingAddressId, Long addressLevel1Id, Long addressLevel2Id, String addressLevel3) {
        this.buildingAddressId = buildingAddressId;
        this.addressLevel1Id = addressLevel1Id;
        this.addressLevel2Id = addressLevel2Id;
        this.addressLevel3 = addressLevel3;
    }

    public void updateBuildingAddress(Long addressLevel1Id, Long addressLevel2Id, String addressLevel3) {
        this.addressLevel1Id = addressLevel1Id;
        this.addressLevel2Id = addressLevel2Id;
        this.addressLevel3 = addressLevel3;
    }
}
