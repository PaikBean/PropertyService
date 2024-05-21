package com.propertyservice.propertyservice.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionTypeDto {
    private Long transactionTypeId;
    private String transactionTypeName;
    private String transactionTypeCode;

    @Builder
    public TransactionTypeDto(Long transactionTypeId, String transactionTypeName, String transactionTypeCode) {
        this.transactionTypeId = transactionTypeId;
        this.transactionTypeName = transactionTypeName;
        this.transactionTypeCode = transactionTypeCode;
    }
}
