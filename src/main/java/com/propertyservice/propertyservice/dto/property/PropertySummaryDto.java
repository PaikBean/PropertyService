package com.propertyservice.propertyservice.dto.property;

import com.propertyservice.propertyservice.domain.common.TransactionState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertySummaryDto {
    private Long propertyId;
    private String unitNumber;
    private Long propertyTypeId;
    private TransactionType transactionType;
    private String price;
    private TransactionState transactionState;

    @Builder
    public PropertySummaryDto(Long propertyId, String unitNumber, Long propertyTypeId, TransactionType transactionType, String price, TransactionState transactionState) {
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.propertyTypeId = propertyTypeId;
        this.transactionType = transactionType;
        this.price = price;
        this.transactionState = transactionState;
    }
}
