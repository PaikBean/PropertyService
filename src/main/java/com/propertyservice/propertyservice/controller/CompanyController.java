package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.company.CompanyRegistryForm;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.service.CompanyService;
import com.propertyservice.propertyservice.service.ManagerService;
import com.propertyservice.propertyservice.utils.validation.ValidBizRegNumber;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;
    private final ManagerService managerService;
    /**
     * 회사 코드 검증
     *
     * @param companyCode
     * @return
     */
    @GetMapping("/v1/validate/company-code")
    public Response validateCompanyCode(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        try {
            return new Response(ResponseCode.SUCCESS, companyService.validateCompanyCode(companyCode), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 사업자 등록번호 진위확인
     *
     * @param bizNumberValidateRequestForm
     * @param bindingResult
     * @return
     */
    @GetMapping("/v1/validate/biz-number")
    public Response validateBizNumber(@Valid BizNumberValidateRequestForm bizNumberValidateRequestForm, BindingResult bindingResult) {
        try {
            return ValidBizRegNumber.validateBizNumber(bizNumberValidateRequestForm) ?
                    new Response(ResponseCode.SUCCESS, null, "200") :           // 진위확인 결과 true
                    new Response(ResponseCode.SUCCESS, null, "204");            // 진위확인 결과 false
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "200");
        }
    }

    /**
     * 회사 등록
     *
     * @param companyRegistryForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/v1/company")
    public Response registerCompany(@RequestBody @Valid CompanyRegistryForm companyRegistryForm, BindingResult bindingResult) {
        Map<String, String> result = new HashMap<>();
        try {
            ManagerSignUpForm presidentInfo = companyRegistryForm.getPresidentInfo();
            String companyCode = companyService.registerCompany(companyRegistryForm);
            presidentInfo.setCompanyCode(companyCode);
            presidentInfo.setDepartmentId(-1L);                 // 대표 회원가입 때 부서 등록하지 않음 >> 조직관리페이지에서 부서 관리 가능
            managerService.createManager(presidentInfo);        // 매니저 회원가입 메소드 재사용
            result.put("companyCode", companyCode);
            return new Response(ResponseCode.SUCCESS, result, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
