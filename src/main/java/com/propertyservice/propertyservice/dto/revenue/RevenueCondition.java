package com.propertyservice.propertyservice.dto.revenue;

import lombok.Getter;

@Getter
public class RevenueCondition {
    private Long managerId;
    private String ownerName;
    private Long addressL1Id;
    private Long addressL2Id;
    private String contractStartDate;
    private String contractEndDate;
}
