package com.propertyservice.propertyservice.dto.property;

import com.propertyservice.propertyservice.domain.common.TransactionState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.PropertyType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertySummaryDto {
    private Long propertyId;
    private String unitNumber;
    private PropertyType propertyType;
    private TransactionType transactionType;
    private String price;
    private TransactionState transactionState;

    @Builder
    public PropertySummaryDto(Long propertyId, String unitNumber, PropertyType propertyType, TransactionType transactionType, String price, TransactionState transactionState) {
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.propertyType = propertyType;
        this.transactionType = transactionType;
        this.price = price;
        this.transactionState = transactionState;
    }
}
