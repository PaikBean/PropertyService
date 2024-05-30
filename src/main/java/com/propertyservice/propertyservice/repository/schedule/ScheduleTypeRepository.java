package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTypeRepository extends JpaRepository<ScheduleType, Long> {
}
