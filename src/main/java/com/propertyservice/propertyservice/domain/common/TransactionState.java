package com.propertyservice.propertyservice.domain.common;

import lombok.Getter;

@Getter
public enum TransactionState {

    SALE("거래 중"),
    SOLD("거래 완료");

    private final String label;

    TransactionState(String label){
        this.label = label;
    }
}
