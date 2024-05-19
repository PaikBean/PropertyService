package com.propertyservice.propertyservice.dto.inflowtype;

import com.propertyservice.propertyservice.domain.InflowType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InflowTypeDto {
    private Long inflowTypeId;
    private String inflowType;

    @Builder
    public InflowTypeDto(InflowType inflowType) {
        this.inflowTypeId = inflowType.getId();
        this.inflowType = inflowType.getInflowType();
    }
}
