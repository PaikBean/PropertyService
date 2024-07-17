package com.propertyservice.propertyservice.dto.revenue;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RevenueForm {
    @NotNull
    private Long managerId;
    private String ownerName;
    private String clientName;
    private Long addressL1;
    private Long addressL2;
    private String addressL3;
    private String contractStartDate;
    private String contractEndDate;
    @NotNull
    private TransactionType transactionType;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    @NotNull
    private BigDecimal commission;
    private String remark;

    @Builder
    public RevenueForm(Long managerId, String ownerName, String clientName, Long addressL1, Long addressL2, String addressL3, String contractStartDate, String contractEndDate, TransactionType transactionType, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal commission, String remark) {
        this.managerId = managerId;
        this.ownerName = ownerName;
        this.clientName = clientName;
        this.addressL1 = addressL1;
        this.addressL2 = addressL2;
        this.addressL3 = addressL3;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.transactionType = transactionType;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.commission = commission;
        this.remark = remark;
    }
}
