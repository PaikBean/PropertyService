package com.propertyservice.propertyservice.dto.schedule;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSummaryDto {
    private Long scheduleId;
    private Long managerId;
    private String managerName;
    private Long clientId;
    private String clientName;
    private Long scheduleTypeId;
    private String scheduleType;
    private String priority;
    private LocalDateTime scheduleDate;

    @QueryProjection

    public ScheduleSummaryDto(Long scheduleId, Long managerId, String managerName, Long clientId, String clientName, Long scheduleTypeId, String scheduleType, String priority, LocalDateTime scheduleDate) {
        this.scheduleId = scheduleId;
        this.managerId = managerId;
        this.managerName = managerName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.scheduleTypeId = scheduleTypeId;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
    }
}
