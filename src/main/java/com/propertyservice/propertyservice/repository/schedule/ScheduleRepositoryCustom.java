package com.propertyservice.propertyservice.repository.schedule;

import com.propertyservice.propertyservice.dto.schedule.ScheduleCondition;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;

import java.util.List;

public interface ScheduleRepositoryCustom {
    List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition);
}
