package com.propertyservice.propertyservice.dto.schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleTypeDto {
    private Long scheduleId;
    private String scheduleType;

    @Builder
    public ScheduleTypeDto(Long scheduleId, String scheduleType) {
        this.scheduleId = scheduleId;
        this.scheduleType = scheduleType;
    }
}
