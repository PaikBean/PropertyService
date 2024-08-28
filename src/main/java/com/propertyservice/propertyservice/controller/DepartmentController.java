package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.company.DepartmentForm;
import com.propertyservice.propertyservice.dto.company.DepartmentInfoForm;
import com.propertyservice.propertyservice.dto.company.DepartmentMemberUpdateForm;
import com.propertyservice.propertyservice.dto.company.DepartmentTotalRevenueCondition;
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
    public Response createDepartment(@RequestBody DepartmentForm departmentForm) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.createDepartment(departmentForm), "201");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


    /**
     * JWT( securtyContentHolder ) 부서 목록 검색.
     *
     * @return
     */
    @GetMapping("/v1/department-list")
    public Response searchDepartmentList() {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * companyId기반 부서 목록 검색.
     *
     * @return
     */
    @GetMapping("/v1/department-list/{companyId}")
    public Response searchDepartmentList(@PathVariable(name = "companyId") Long companyId) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentList(companyId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 정보 단건 조회
     *
     * @param departmentId
     * @return
     */
    @GetMapping("/v1/department-info/{departmentId}")
    public Response searchDepartmentInfo(@PathVariable(name = "departmentId") Long departmentId) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentInfo(departmentId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 목록 조회.
     *
     * @param departmentId
     * @return
     */
    @GetMapping("/v1/manager-list/{departmentId}")
    public Response searchManagerListForDepartment(@PathVariable("departmentId") Long departmentId) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchManagerInfoListByDepartmentId(departmentId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 정보 수정.
     *
     * @param departmentInfoForm
     * @return
     */
    @PutMapping("/v1/department-info")
    public Response updateDepartmentInfo(@RequestBody DepartmentInfoForm departmentInfoForm) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.updateDepartmentInfo(departmentInfoForm), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 총 매출액 조회. (기간내)
     *
     * @param departmentTotalRevenueCondition
     * @return
     */
    @GetMapping("/v1/total-revenue/")
    public Response searchDepartmentTotalRevenue(DepartmentTotalRevenueCondition departmentTotalRevenueCondition) {
        try {
            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentTotalRevenue(departmentTotalRevenueCondition), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서 삭제
     *
     * @param departmentId
     * @return
     */
    @DeleteMapping("/v1/department-info/{departmentId}")
    public Response deleteDepartment(@PathVariable(name = "departmentId") Long departmentId) {
        try {
            departmentService.deleteDepartment(departmentId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 부서원 목록 수정
     *
     * @param departmentMemberUpdateForm
     * @return
     */
    @PutMapping("/v1/department-member/")
    public Response updateDepartmentMember(@RequestBody DepartmentMemberUpdateForm departmentMemberUpdateForm) {
        try {
            departmentService.updateDepartmentMember(departmentMemberUpdateForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


    //    /**
//     * 부서 목록 검색 by 회사 코드
//     *
//     * @param companyCode
//     * @return
//     */
//    @GetMapping("/v1/department-list")
//    public Response searchDepartmentList(@RequestParam(name = "companyCode", defaultValue = "") String companyCode) {
//        try {
//            return new Response(ResponseCode.SUCCESS, departmentService.searchDepartmentList(companyCode), "200");
//        } catch (Exception e) {
//            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
//        }
//    }
}
