package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/v1/validate/company-code")
    public Response searchCompanyByCompanyCode(@RequestParam(value = "companyCode", defaultValue = "") String companyCode ){
        try {
            companyService.searchCompanyByCompanyCode(companyCode);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
