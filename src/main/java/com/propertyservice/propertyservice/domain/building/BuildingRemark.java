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
@Table(name = "building_remark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingRemark extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remark_id")
    private Long remarkId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "building_id")
    private Building building;
    private String remark;

    @Builder
    public BuildingRemark(Long remarkId, Building building, String remark) {
        this.remarkId = remarkId;
        this.building = building;
        this.remark = remark;
    }
}
