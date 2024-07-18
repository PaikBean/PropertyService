package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.PropertyType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShowingPropertyCandidateCondition {
    private String ownerName;
    private Long addressLevel1Id;
    private Long addressLevel2Id;
    private TransactionType propertyTransactionType;
    private PropertyType propertyType;
}
