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
@Table(name = "building")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Building extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long buildingId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "building_address_id")
    private BuildingAddress buildingAddress;

    @Builder
    public Building(Long buildingId, Owner owner, BuildingAddress buildingAddress) {
        this.buildingId = buildingId;
        this.owner = owner;
        this.buildingAddress = buildingAddress;
    }
}
