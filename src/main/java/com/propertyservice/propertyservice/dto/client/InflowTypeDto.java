package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.client.InflowType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InflowTypeDto {

    private String inflowTypeName;
    private String label;

    @Builder
    public InflowTypeDto(String inflowTypeName, String label) {
        this.inflowTypeName = inflowTypeName;
        this.label = label;
    }
}
