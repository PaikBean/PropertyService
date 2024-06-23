package com.propertyservice.propertyservice.dto.revenue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RevenueCondition {
    private Long managerId;
    private Long addressL1Id;
    private Long addressL2Id;
    private String contractStartDate;
    private String contractEndDate;
    private Long transactionType;
}
