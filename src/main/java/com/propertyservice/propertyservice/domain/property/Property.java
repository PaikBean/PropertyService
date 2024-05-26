package com.propertyservice.propertyservice.domain.property;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "property")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Property extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "building_id")
    private Building building;
    @Column(nullable = false)
    private String unitNumber;
    private Long picManagerId;
    @Column(nullable = false)
    private Long transactionTypeId;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    private BigDecimal maintenanceFee;
    @OneToOne
    @JoinColumn(name = "maintenance_item_id")
    private MaintenanceItem maintenanceItem;

    @Builder
    public Property(Long propertyId, Building building, String unitNumber, Long picManagerId, Long transactionTypeId, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal maintenanceFee, MaintenanceItem maintenanceItem) {
        this.propertyId = propertyId;
        this.building = building;
        this.unitNumber = unitNumber;
        this.picManagerId = picManagerId;
        this.transactionTypeId = transactionTypeId;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.maintenanceFee = maintenanceFee;
        this.maintenanceItem = maintenanceItem;
    }
}
