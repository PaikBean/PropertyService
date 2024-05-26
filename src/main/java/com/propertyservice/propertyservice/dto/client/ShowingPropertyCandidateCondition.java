package com.propertyservice.propertyservice.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShowingPropertyCandidateCondition {
    private String ownerName;
    private Long addressLevel1Id;
    private Long addressLevel2Id;
    private Long propertyTransactionTypeId;
    private Long propertyTypeId;
}
