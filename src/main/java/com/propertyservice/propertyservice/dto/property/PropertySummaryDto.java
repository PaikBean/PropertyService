package com.propertyservice.propertyservice.dto.property;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertySummaryDto {
    private Long propertyId;
    private String unitNumber;
    private Long propertyTypeId;
    private Long transactionTypeId;
    private String price;
    private Long transactionStateId;

    @Builder
    public PropertySummaryDto(Long propertyId, String unitNumber, Long propertyTypeId, Long transactionTypeId, String price, Long transactionStateId) {
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.propertyTypeId = propertyTypeId;
        this.transactionTypeId = transactionTypeId;
        this.price = price;
        this.transactionStateId = transactionStateId;
    }
}
