package com.propertyservice.propertyservice.dto.property;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class PropertyDto {
    private Long propertyId;
    private String unitNumber;
    private Long picManagerId;
    private String picManagerName;
    private Long transactionTypeId;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    private BigDecimal maintenanceFee;
    private boolean maintenanceItemWater;
    private boolean maintenanceItemElectricity;
    private boolean maintenanceItemInternet;
    private boolean maintenanceItemGas;
    private String maintenanceItemOthers;
    private Long transactionStateId;

    private List<PropertyRemarkDto> propertyRemarkDtoList;

    @Builder
    public PropertyDto(Long propertyId, String unitNumber, Long picManagerId, String picManagerName, Long transactionTypeId, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal maintenanceFee, boolean maintenanceItemWater, boolean maintenanceItemElectricity, boolean maintenanceItemInternet, boolean maintenanceItemGas, String maintenanceItemOthers, Long transactionStateId, List<PropertyRemarkDto> propertyRemarkDtoList) {
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.picManagerId = picManagerId;
        this.picManagerName = picManagerName;
        this.transactionTypeId = transactionTypeId;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.maintenanceFee = maintenanceFee;
        this.maintenanceItemWater = maintenanceItemWater;
        this.maintenanceItemElectricity = maintenanceItemElectricity;
        this.maintenanceItemInternet = maintenanceItemInternet;
        this.maintenanceItemGas = maintenanceItemGas;
        this.maintenanceItemOthers = maintenanceItemOthers;
        this.transactionStateId = transactionStateId;
        this.propertyRemarkDtoList = propertyRemarkDtoList;
    }
}
