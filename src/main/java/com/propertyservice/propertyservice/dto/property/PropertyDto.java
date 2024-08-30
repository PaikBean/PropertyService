package com.propertyservice.propertyservice.dto.property;

import com.propertyservice.propertyservice.domain.common.TransactionState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.PropertyType;
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
    private PropertyType propertyType;
    private TransactionType transactionType;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    private BigDecimal maintenanceFee;
    private BigDecimal shortTermDeposit;
    private BigDecimal shortTermMonthlyFee;
    private boolean maintenanceItemWater;
    private boolean maintenanceItemElectricity;
    private boolean maintenanceItemInternet;
    private boolean maintenanceItemGas;
    private String maintenanceItemOthers;
    private TransactionState transactionState;
    private BigDecimal commision;
    private String remark;

    //private List<PropertyRemarkDto> propertyRemarkDtoList;

    @Builder
    public PropertyDto(Long propertyId, String unitNumber, Long picManagerId, String picManagerName, PropertyType propertyType, TransactionType transactionType, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal shortTermDeposit, BigDecimal shortTermMonthlyFee, BigDecimal maintenanceFee, boolean maintenanceItemWater, boolean maintenanceItemElectricity, boolean maintenanceItemInternet, boolean maintenanceItemGas, String maintenanceItemOthers, TransactionState transactionState, BigDecimal commision, String remark) {
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.picManagerId = picManagerId;
        this.picManagerName = picManagerName;
        this.propertyType = propertyType;
        this.transactionType = transactionType;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.shortTermDeposit = shortTermDeposit;
        this.shortTermMonthlyFee = shortTermMonthlyFee;
        this.maintenanceFee = maintenanceFee;
        this.maintenanceItemWater = maintenanceItemWater;
        this.maintenanceItemElectricity = maintenanceItemElectricity;
        this.maintenanceItemInternet = maintenanceItemInternet;
        this.maintenanceItemGas = maintenanceItemGas;
        this.maintenanceItemOthers = maintenanceItemOthers;
        this.transactionState = transactionState;
        this.commision = commision;
        this.remark = remark;
        //this.propertyRemarkDtoList = propertyRemarkDtoList;
    }
}
