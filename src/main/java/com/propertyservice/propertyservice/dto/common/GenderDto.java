package com.propertyservice.propertyservice.dto.common;


import lombok.Builder;
import lombok.Getter;

@Getter
public class GenderDto {
    private String name;
    private String label;

    @Builder
    public GenderDto(String name, String label) {
        this.name = name;
        this.label = label;
    }
}