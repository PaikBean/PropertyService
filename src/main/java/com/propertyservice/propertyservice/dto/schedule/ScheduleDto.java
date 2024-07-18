package com.propertyservice.propertyservice.dto.schedule;

import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDto {
    private Long scheduleId;
    private Long managerId;
    private String managerName;
    private Long clientId;
    private String clientName;
    private ScheduleType scheduleType;
    private String priority;
    private LocalDateTime scheduleDate;
    private String remark;

    @Builder
    public ScheduleDto(Long scheduleId, Long managerId, String managerName, Long clientId, String clientName, ScheduleType scheduleType, String priority, LocalDateTime scheduleDate, String remark) {
        this.scheduleId = scheduleId;
        this.managerId = managerId;
        this.managerName = managerName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
        this.remark = remark;
    }
}
