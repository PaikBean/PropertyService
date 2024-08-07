package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.domain.schedule.Schedule;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.*;
import com.propertyservice.propertyservice.repository.client.ClientRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ClientRepository clientRepository;
    private final CommonService commonService;
    private final ManagerRepository managerRepository;


    public List<ScheduleTypeDto> searchScheduleTypeList() {
        return Arrays.stream(ScheduleType.values())
                .map(scheduleType -> new ScheduleTypeDto(scheduleType.name(), scheduleType.getLabel()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createSchedule(ScheduleForm scheduleForm) {
        scheduleRepository.save(Schedule.builder()
                .managerId(managerRepository.findById(scheduleForm.getManagerId()).orElseThrow(
                        () -> new EntityNotFoundException("매니저를 찾을 수 없습니다.")).getManagerId()
                )
                .clientId(clientRepository.findById(scheduleForm.getClientId()).orElseThrow(
                        () -> new EntityNotFoundException("고객을 찾을 수 없습니다.")).getClientId())
                .scheduleDate(
                        LocalDate.parse(scheduleForm.getScheduleDate(),
                                DateTimeFormatter.ofPattern("yyyyMMdd")
                        ).atStartOfDay())
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
                        LocalDate.parse(
                                scheduleForm.getScheduleDate(),
                                DateTimeFormatter.ofPattern("yyyyMMdd")
                        ).atStartOfDay(),
                        scheduleForm.getRemark()
                );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public ScheduleDto searchSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 일정입니다."));

        Optional<Client> client = clientRepository.findById(schedule.getClientId());

        return ScheduleDto.builder()
                .scheduleId(scheduleId)
                .managerId(schedule.getManagerId())
                .clientId(client.isPresent() ? client.get().getClientId() : null)
                .clientName(client.isPresent() ? client.get().getClientName() : null)
                .scheduleType(schedule.getScheduleType())
                .priority(schedule.getPriority())
                .scheduleDate(
                        schedule.getScheduleDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                )
                .remark(schedule.getRemark())
                .build();
    }

    public List<ScheduleSummaryDto> searchScheduleList(ScheduleCondition scheduleCondition) {
        Long companyId = commonService.getCustomUserDetailBySecurityContextHolder().getCompany().getCompanyId();
        if(companyId == null)
            throw new IllegalArgumentException("등록 회사를 찾을 수 없습니다.");
        scheduleCondition.setCompanyId(companyId);
        return scheduleRepository.searchScheduleList(scheduleCondition);
    }

    public List<ScheduleSummaryDto> searchScheduleList(Long clientId) {
        return scheduleRepository.searchScheduleList(clientId);
    }

    /**
     * 고객 정보 단건 조회 - 고객 상세 ( 일정표)
     */
    public List<ScheduleSummaryDto> searchScheduleListByClientId(Long clientId) {
        return scheduleRepository.searchScheduleListByClientId(clientId);
    }
}
