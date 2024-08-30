package com.propertyservice.propertyservice.dto.property;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PropertyTypeDto {
    private String propertyTypeName;
    private String label;

    @Builder
    public PropertyTypeDto(String propertyTypeName, String label) {
        this.propertyTypeName = propertyTypeName;
        this.label = label;
    }
}
