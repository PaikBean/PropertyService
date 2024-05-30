package com.propertyservice.propertyservice.domain.schedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedule_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_type_id")
    private Long scheduleTypeId;
    @Column(nullable = false)
    private String scheduleType;

    @Builder
    public ScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }
}
