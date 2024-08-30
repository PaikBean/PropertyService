package com.propertyservice.propertyservice.dto.schedule;

import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ScheduleSummaryDto {
    private Long scheduleId;
    private Long managerId;
    private String managerName;
    private Long clientId;
    private String clientName;
    private ScheduleType scheduleType;
    private String priority;
    private LocalDateTime scheduleDate;
    private String scheduleDateTxt;
    private String remark;

    @QueryProjection
    public ScheduleSummaryDto(Long scheduleId, Long managerId, String managerName, Long clientId, String clientName, ScheduleType scheduleType, String priority, LocalDateTime scheduleDate) {
        this.scheduleId = scheduleId;
        this.managerId = managerId;
        this.managerName = managerName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
    }

    /**
     * 고객 정보 단건 조회.
     */
    @QueryProjection
    public ScheduleSummaryDto(Long scheduleId, String managerName, ScheduleType scheduleType, String priority, LocalDateTime scheduleDate) {
        this.scheduleId = scheduleId;
        this.managerName = managerName;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDateTxt = scheduleDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));;
    }
    @QueryProjection
    public ScheduleSummaryDto(Long scheduleId, String managerName, ScheduleType scheduleType, LocalDateTime scheduleDate, String remark) {
        this.scheduleId = scheduleId;
        this.managerName = managerName;
        this.scheduleType = scheduleType;
        this.remark = remark;
        this.scheduleDateTxt = scheduleDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));;
    }
}
