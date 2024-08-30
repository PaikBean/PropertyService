package com.propertyservice.propertyservice.dto.revenue;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class RevenueDto {
    private Long id;
    private String managerName;
    private String ownerName;
    private String clientName;
    private String address;
    private String contractStartDate;
    private String contractEndDate;
    private String transactionType;
    @Setter
    private String price;
    @Setter
    private String commission;
    private String remark;

    @QueryProjection
    public RevenueDto(Long revenueId, String managerName, String ownerName, String clientName, String address, String contractStartDate, String contractEndDate, String transactionType, String price, String commission, String remark) {
        this.id = revenueId;
        this.managerName = managerName;
        this.ownerName = ownerName;
        this.clientName = clientName;
        this.address = address;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.transactionType = transactionType;
        this.price = price;
        this.commission = commission;
        this.remark = remark;
    }
}
