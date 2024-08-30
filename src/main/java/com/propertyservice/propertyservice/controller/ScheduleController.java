package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.schedule.*;
import com.propertyservice.propertyservice.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            return new Response(ResponseCode.SUCCESS, scheduleService.searchScheduleTypeList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 일정 등록
     * @param scheduleForm
     * @return
     */
    @PostMapping("/v1/schedule")
    public Response createSchedule(@RequestBody @Valid ScheduleForm scheduleForm) {
        try {
            scheduleService.createSchedule(scheduleForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (EntityNotFoundException e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 일정 수정
     *
     * @param scheduleForm
     * @param bindingResult
     * @return
     */
    @PutMapping("/v1/schedule")
    public Response updateSchedule(@RequestBody @Valid ScheduleForm scheduleForm, BindingResult bindingResult) {
        try {
            scheduleService.updateSchedule(scheduleForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (EntityNotFoundException e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


    /**
     * 일정 삭제
     * @param scheduleId
     * @return
     */
    @DeleteMapping("/v1/schedule/{scheduleId}")
    public Response deleteSchedule(@PathVariable(name = "scheduleId") Long scheduleId) {
        try {
            scheduleService.deleteSchedule(scheduleId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 일정 상세 조회
     *
     * @param scheduleId
     * @return
     */
    @GetMapping("/v1/schedule/{scheduleId}")
    public Response searchSchedule(@PathVariable(name = "scheduleId") Long scheduleId) {
        try {
            return new Response(ResponseCode.SUCCESS, scheduleService.searchSchedule(scheduleId), "200");
        } catch (EntityNotFoundException e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 일정 목록 조회
     *
     * @param scheduleCondition
     * @return
     */
    @GetMapping("/v1/schedule-list")
    public Response serachScheduleList(ScheduleCondition scheduleCondition) {
        try {
            List<ScheduleSummaryDto> scheduleSummaryDtoList = scheduleService.searchScheduleList(scheduleCondition);
            return scheduleSummaryDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, scheduleSummaryDtoList, "200")
                    : new Response(ResponseCode.SUCCESS, scheduleSummaryDtoList, "204");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


}
