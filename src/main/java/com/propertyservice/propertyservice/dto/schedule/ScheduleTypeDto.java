package com.propertyservice.propertyservice.dto.schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleTypeDto {
    private String scheduleTypeName;
    private String label;

    @Builder
    public ScheduleTypeDto(String scheduleTypeName, String label) {
        this.scheduleTypeName = scheduleTypeName;
        this.label = label;
    }
}
