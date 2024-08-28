package com.propertyservice.propertyservice.dto.property;

import com.propertyservice.propertyservice.domain.common.TransactionState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PropertyForm {
    private Long propertyId;
    private Long buildingId;
    private Long picManagerId;
    private PropertyType propertyType;
    @NotNull @NotBlank
    private String unitNumber;
    //@NotNull
    private TransactionType transactionType;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    private BigDecimal shortTermDeposit;
    private BigDecimal shortTermMonthlyFee;
    private boolean maintenanceItemWater; // 관리비 수도
    private boolean maintenanceItemElectricity; // 관리비 전기
    private boolean maintenanceItemInternet; // 관리비 인터넷
    private boolean maintenanceItemGas; // 관리비 난방
    private String maintenanceItemOthers;// 관리비 기타
    private BigDecimal maintenanceFee; // 관리비
    private TransactionState transactionState;
    private BigDecimal commision; // 중개수수료
    private String remark;
}
