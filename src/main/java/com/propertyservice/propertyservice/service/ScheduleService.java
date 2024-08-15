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
    private final EntityExceptionService entityExceptionService;


    public List<ScheduleTypeDto> searchScheduleTypeList() {
        return Arrays.stream(ScheduleType.values())
                .map(scheduleType -> new ScheduleTypeDto(scheduleType.name(), scheduleType.getLabel()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createSchedule(ScheduleForm scheduleForm) {
        scheduleRepository.save(Schedule.builder()
                .managerId(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findByManagerId(scheduleForm.getManagerId()),
                                "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                )
                .clientId(
                        entityExceptionService.findEntityById(
                                () -> clientRepository.findById(scheduleForm.getClientId()),
                                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getClientId()
                )
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
        entityExceptionService.findEntityById(
                        () -> scheduleRepository.findById(scheduleForm.getScheduleId()),
                        "일정 정보가 존재하지 않습니다. 관리자에게 문의하세요.")
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
        scheduleRepository.delete(entityExceptionService.findEntityById(
                () -> scheduleRepository.findById(scheduleId),
                "일정 정보가 존재하지 않습니다. 관리자에게 문의하세요.")
        );
    }

    public ScheduleDto searchSchedule(Long scheduleId) {
        Schedule schedule = entityExceptionService.findEntityById(
                    () -> scheduleRepository.findById(scheduleId),
                "일정 정보가 존재하지 않습니다. 관리자에게 문의하세요.");

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
        entityExceptionService.validateEntityExists(
                () -> clientRepository.findById(clientId),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.");
        return scheduleRepository.searchScheduleList(clientId);
    }

    /**
     * 고객 정보 단건 조회 - 고객 상세 ( 일정표)
     */
    public List<ScheduleSummaryDto> searchScheduleListByClientId(Long clientId) {
        entityExceptionService.validateEntityExists(
                () -> clientRepository.findById(clientId),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.");
        return scheduleRepository.searchScheduleListByClientId(clientId);
    }
}
