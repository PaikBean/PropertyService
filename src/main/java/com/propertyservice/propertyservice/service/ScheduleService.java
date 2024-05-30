package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.ScheduleTypeDto;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepository;
import com.propertyservice.propertyservice.repository.schedule.ScheduleTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleTypeRepository scheduleTypeRepository;
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleTypeDto> searchScheduleTypeList() {
        List<ScheduleTypeDto> scheduleTypeDtoList = new ArrayList<>();
        for (ScheduleType scheduleType : scheduleTypeRepository.findAll()) {
            scheduleTypeDtoList.add(ScheduleTypeDto.builder()
                    .scheduleId(scheduleType.getScheduleTypeId())
                    .scheduleType(scheduleType.getScheduleType())
                    .build());
        }
        return scheduleTypeDtoList;
    }
}
