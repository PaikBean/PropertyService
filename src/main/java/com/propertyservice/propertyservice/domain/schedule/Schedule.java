package com.propertyservice.propertyservice.domain.schedule;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    private Long managerId;
    private Long clientId;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_type_id")
    private ScheduleType scheduleType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    private LocalDateTime scheduleDate;
    private String remark;

    @Builder
    public Schedule(Long managerId, Long clientId, ScheduleType scheduleType, Priority priority, LocalDateTime scheduleDate, String remark) {
        this.managerId = managerId;
        this.clientId = clientId;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
        this.remark = remark;
    }

    public void updateSchedule(Long managerId, Long clientId, ScheduleType scheduleType, Priority priority, LocalDateTime scheduleDate, String remark) {
        this.managerId = managerId;
        this.clientId = clientId;
        this.scheduleType = scheduleType;
        this.priority = priority;
        this.scheduleDate = scheduleDate;
        this.remark = remark;
    }
}
