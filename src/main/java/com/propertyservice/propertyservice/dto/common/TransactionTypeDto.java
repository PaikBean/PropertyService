package com.propertyservice.propertyservice.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionTypeDto {
    private String transactionTypeName;
    private String label;

    @Builder
    public TransactionTypeDto(String transactionTypeName, String label) {
        this.transactionTypeName = transactionTypeName;
        this.label = label;
    }
}
