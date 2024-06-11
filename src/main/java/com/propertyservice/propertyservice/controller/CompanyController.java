package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.service.CompanyService;
import com.propertyservice.propertyservice.utils.validation.ValidBizRegNumber;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 회사 코드 검증
     *
     * @param companyCode
     * @return
     */
    @GetMapping("/v1/validate/company-code")
    public Response searchCompanyByCompanyCode(@RequestParam(value = "companyCode", defaultValue = "") String companyCode) {
        try {
            return new Response(ResponseCode.SUCCESS, companyService.searchCompanyByCompanyCode(companyCode), "200");
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
}
