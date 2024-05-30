package com.propertyservice.propertyservice.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleCondition {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long scheduleTypeId;
    private Long managerId;
    private Long clientId;
}
