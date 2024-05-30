package com.propertyservice.propertyservice.domain.schedule;


import lombok.Getter;

@Getter
public enum Priority {
    HIGH("1"),
    MEDIUM("2"),
    LOW("3");

    private final String label;

    Priority(String label) {
        this.label = label;
    }
}