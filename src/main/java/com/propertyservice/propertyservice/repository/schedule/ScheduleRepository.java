package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {
}
