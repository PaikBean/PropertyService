package com.propertyservice.propertyservice.domain.property;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import com.propertyservice.propertyservice.domain.common.TransactionState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
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
    private Long picManagerId; // 담당매니저.
    private PropertyType propertyType;
    @Column(nullable = false)
    private String unitNumber;
    private TransactionType transactionType;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    private BigDecimal shortTermDeposit;
    private BigDecimal shortTermMonthlyFee;
    private BigDecimal maintenanceFee;
    @OneToOne
    @JoinColumn(name = "maintenance_item_id")
    private MaintenanceItem maintenanceItem;

    private TransactionState transactionState;
    private BigDecimal commision;

    @Builder
    public Property(Long propertyId, Building building, String unitNumber, Long picManagerId, PropertyType propertyType, TransactionType transactionType, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal shortTermDeposit, BigDecimal shortTermMonthlyFee, BigDecimal maintenanceFee, MaintenanceItem maintenanceItem, TransactionState transactionState, BigDecimal commision) {
        this.propertyId = propertyId;
        this.building = building;
        this.unitNumber = unitNumber;
        this.picManagerId = picManagerId;
        this.propertyType = propertyType;
        this.transactionType = transactionType;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.shortTermDeposit = shortTermDeposit;
        this.shortTermMonthlyFee = shortTermMonthlyFee;
        this.maintenanceFee = maintenanceFee;
        this.maintenanceItem = maintenanceItem;
        this.transactionState = transactionState;
        this.commision = commision;
    }

    public void updateProperty(String unitNumber, Long picManagerId, PropertyType propertyType, TransactionType transactionType, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal maintenanceFee, MaintenanceItem maintenanceItem, TransactionState transactionState) {
        this.unitNumber = unitNumber;
        this.picManagerId = picManagerId;
        this.propertyType = propertyType;
        this.transactionType = transactionType;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.maintenanceFee = maintenanceFee;
        this.maintenanceItem = maintenanceItem;
        this.transactionState = transactionState;
    }
}
