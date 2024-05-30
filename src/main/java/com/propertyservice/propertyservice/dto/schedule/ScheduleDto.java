package com.propertyservice.propertyservice.dto.schedule;

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
    private Long scheduleTypeId;
    private String scheduleType;
    private String priority;
    private LocalDateTime scheduleDate;
    private String remark;

    @Builder
    public ScheduleDto(Long scheduleId, Long managerId, String managerName, Long clientId, String clientName, Long scheduleTypeId, String scheduleType, String priority, LocalDateTime scheduleDate, String remark) {
        this.scheduleId = scheduleId;
        this.managerId = managerId;
        this.managerName = managerName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.scheduleTypeId = scheduleTypeId;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
        this.remark = remark;
    }
}
