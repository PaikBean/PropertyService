package com.propertyservice.propertyservice.dto.revenue;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RevenueCondition {
    private Long companyId;
    private Long managerId;
    private Long addressL1Id;
    private Long addressL2Id;
    private String contractStartDate;
    private String contractEndDate;
    private TransactionType transactionType;
}
