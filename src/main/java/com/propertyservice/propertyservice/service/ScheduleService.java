package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.schedule.Schedule;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.*;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepository;
import com.propertyservice.propertyservice.repository.schedule.ScheduleTypeRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public void createSchedule(ScheduleForm scheduleForm) {
        scheduleRepository.save(Schedule.builder()
                        .managerId(scheduleForm.getManagerId())
                        .clientId(scheduleForm.getClientId())
                        .scheduleDate(scheduleForm.getScheduleDate())
                        .scheduleType(scheduleTypeRepository.findById(scheduleForm.getScheduleTypeId()).orElseThrow(
                                () -> new EntityNotFoundException("등록되지 않은 일정입니다.")))
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
                        scheduleTypeRepository.findById(scheduleForm.getScheduleTypeId()).orElseThrow(
                                () -> new EntityNotFoundException("등록되지 않은 일정 유형입니다.")),
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
                .scheduleTypeId(schedule.getScheduleId())
                .scheduleType(scheduleTypeRepository.findById(schedule.getScheduleId()).orElseThrow(
                        () -> new EntityNotFoundException("등록되지 않은 일정 유형입니다.")
                    ).getScheduleType())
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
