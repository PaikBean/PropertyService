package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.domain.schedule.ScheduleType;
import com.propertyservice.propertyservice.dto.schedule.ScheduleTypeDto;
import com.propertyservice.propertyservice.repository.schedule.ScheduleRepository;
import com.propertyservice.propertyservice.repository.schedule.ScheduleTypeRepository;
import com.propertyservice.propertyservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 유형 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/schedule-type-list")
    public Response searchScheduleTypeList() {
        try {
            List<ScheduleTypeDto> scheduleTypeDtoList = scheduleService.searchScheduleTypeList();
            return scheduleTypeDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, scheduleTypeDtoList, "200")
                    : new Response(ResponseCode.SUCCESS, scheduleTypeDtoList, "204");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
