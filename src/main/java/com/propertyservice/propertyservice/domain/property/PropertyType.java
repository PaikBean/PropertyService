package com.propertyservice.propertyservice.domain.property;

import lombok.Getter;

@Getter
public enum PropertyType {
    RESIDENTIAL("주거용"),
    COMMERCIAL("상업용");

    private final String label;

    PropertyType(String label){
        this.label = label;
    }
}