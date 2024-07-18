package com.propertyservice.propertyservice.domain.client;

import lombok.Getter;

@Getter
public enum InflowType {

    ZIGBANG("직방"),
    DABANG("다방"),
    PETERPANZ("피터팬"),
    ZIPTOSS("집토스"),
    OTHERS("기타");

    private String label;

    InflowType(String label) {
        this.label = label;
    }
}
