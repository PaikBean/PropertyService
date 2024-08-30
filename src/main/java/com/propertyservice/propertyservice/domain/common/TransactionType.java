package com.propertyservice.propertyservice.domain.common;

import lombok.Getter;

@Getter
public enum TransactionType {
    MONTHLY("월세"),
    JEONSE("전세"),
    TRADE("매매"),
    SHORTERM("단기");

    private final String label;

    TransactionType(String label){
        this.label = label;
    }

}
