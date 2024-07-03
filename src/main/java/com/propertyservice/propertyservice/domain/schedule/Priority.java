package com.propertyservice.propertyservice.domain.schedule;


import lombok.Getter;

@Getter
public enum Priority {
    HIGH("3"),
    MEDIUM("2"),
    LOW("1");

    private final String label;

    Priority(String label) {
        this.label = label;
    }
}