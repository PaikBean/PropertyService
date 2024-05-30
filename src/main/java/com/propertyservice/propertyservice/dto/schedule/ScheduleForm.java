package com.propertyservice.propertyservice.dto.schedule;

import com.propertyservice.propertyservice.domain.schedule.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleForm {
    private Long managerId;
    private Long clientId;
    private Long scheduleTypeId;
    private Priority priority;
}
