package com.propertyservice.propertyservice.dto.schedule;

import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleCondition {
    private Long companyId;
    private String startDate;
    private String endDate;
    private ScheduleType scheduleType;
    private Long managerId;
    private Long clientId;
}
