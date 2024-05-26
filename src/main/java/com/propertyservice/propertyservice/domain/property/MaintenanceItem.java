package com.propertyservice.propertyservice.domain.property;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "maintenance_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MaintenanceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_item_id")
    private Long maintenanceItemId;
    @Column(nullable = false)
    private boolean water;
    @Column(nullable = false)
    private boolean electricity;
    @Column(nullable = false)
    private boolean internet;
    @Column(nullable = false)
    private boolean gas;
    private String others;

    @Builder
    public MaintenanceItem(Long maintenanceItemId, boolean water, boolean electricity, boolean internet, boolean gas, String others) {
        this.maintenanceItemId = maintenanceItemId;
        this.water = water;
        this.electricity = electricity;
        this.internet = internet;
        this.gas = gas;
        this.others = others;
    }
}
