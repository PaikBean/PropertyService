package com.propertyservice.propertyservice.dto.schedule;

import com.propertyservice.propertyservice.domain.schedule.Priority;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleForm {
    private Long scheduleId;
    private Long managerId;
    private Long clientId;
    @NotNull
    private Long scheduleTypeId;
    @NotNull
    private LocalDateTime scheduleDate;
    private Priority priority;
    private String remark;
}
