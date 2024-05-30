package com.propertyservice.propertyservice.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScheduleIdForm {
    @NotNull
    private Long scheduleId;
}
