package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.schedule.Schedule;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.*;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleTypeDto> searchScheduleTypeList() {
        return Arrays.stream(ScheduleType.values())
                .map(scheduleType -> new ScheduleTypeDto(scheduleType.name(), scheduleType.getLabel()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createSchedule(ScheduleForm scheduleForm) {
        scheduleRepository.save(Schedule.builder()
                        .managerId(scheduleForm.getManagerId())
                        .clientId(scheduleForm.getClientId())
                        .scheduleDate(scheduleForm.getScheduleDate())
                        .scheduleType(scheduleForm.getScheduleType())
                        .priority(scheduleForm.getPriority())
                        .remark(scheduleForm.getRemark())
                .build());
    }

    @Transactional
    public void updateSchedule(ScheduleForm scheduleForm) {
        scheduleRepository.findById(scheduleForm.getScheduleId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 일정입니다."))
                .updateSchedule(
                        scheduleForm.getManagerId(),
                        scheduleForm.getClientId(),
                        scheduleForm.getScheduleType(),
                        scheduleForm.getPriority(),
                        scheduleForm.getScheduleDate(),
                        scheduleForm.getRemark()
                );
    }

    @Transactional
    public void deleteSchedule(ScheduleIdForm scheduleIdForm) {
        scheduleRepository.deleteById(scheduleIdForm.getScheduleId());
    }

    public ScheduleDto searchSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 일정입니다."));
        return ScheduleDto.builder()
                .scheduleId(schedule.getScheduleId())
                .managerId(schedule.getManagerId())
                .managerName(null)  // Todo : manager 엔티티 생성되면 수정
                .clientId(schedule.getClientId())
                .clientName(null)   // Todo : client 엔티티 생성되면 수정
                .scheduleType(schedule.getScheduleType())
                .priority(schedule.getPriority().getLabel())
                .scheduleDate(schedule.getScheduleDate())
                .remark(schedule.getRemark())
                .build();
    }

    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        return scheduleRepository.searchScheduleList(scheduleCondition);
    }
    public List<ScheduleSummaryDto> searchScheduleList(Long clientId) {
        return scheduleRepository.searchScheduleList(clientId);
    }
}
