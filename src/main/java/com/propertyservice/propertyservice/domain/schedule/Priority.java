package com.propertyservice.propertyservice.domain.schedule;


import lombok.Getter;

@Getter
public enum Priority {
    HIGH("상"),
    MEDIUM("중"),
    LOW("하");

    private final String label;

    Priority(String label) {
        this.label = label;
    }
}