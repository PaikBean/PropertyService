package com.propertyservice.propertyservice.domain.common;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    UNKNOWN("미상");

    private final String label;

    Gender(String label) {
        this.label = label;
    }
}
