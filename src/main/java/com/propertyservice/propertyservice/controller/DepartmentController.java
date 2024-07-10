package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.company.DepartmentForm;
import com.propertyservice.propertyservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/v1/department")
    public Response createDepartment(DepartmentForm departmentForm) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.createDepartment(departmentForm), "201");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 목록 검색 by 회사 코드
     *
     * @param companyCode
     * @return
     */
    @GetMapping("/v1/department-list")
    public Response searchDepartmentList(@RequestParam(name = "companyCode") String companyCode) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentList(companyCode), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
