package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.dto.company.CompanyInfoForm;
import com.propertyservice.propertyservice.dto.company.CompanyRegistryForm;
import com.propertyservice.propertyservice.dto.company.CompanySimpleDto;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.property.CompanyAddressRepository;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DepartmentService departmentService;
    private final CompanyAddressRepository companyAddressRepository;
    private final ManagerRepository managerRepository;

    public Company searchCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode).orElseThrow(
                () -> new EntityNotFoundException("회사코드가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }

    public Long searchCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new EntityNotFoundException("회사가 존재하지 않습니다.")
        ).getCompanyId();
    }

    @Transactional
    public String registerCompany(CompanyRegistryForm companyRegistryForm) {
        CompanyInfoForm companyInfo = companyRegistryForm.getCompanyInfo();
        ManagerSignUpForm presidentInfo = companyRegistryForm.getPresidentInfo();
        Company company = companyRepository.save(Company.builder()
                .companyCode("temp")
                .companyName(companyInfo.getCompanyName())
                .bizNumber(companyInfo.getBizNumber())
                .presidentName(presidentInfo.getManagerName())
                .companyEmail(companyInfo.getCompanyEmail())
                .companyNumber(companyInfo.getCompanyNumber())
                .serviceStartDate(LocalDateTime.now())
                .serviceEndDate(LocalDateTime.of(9999, 12, 31, 23, 59, 59))
                .build());
        return company.getCompanyCode();
    }

    public CompanySimpleDto validateCompanyCode(String companyCode) {
        Company company = companyRepository.findByCompanyCode(companyCode).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 회사 코드 입니다."));
        return CompanySimpleDto.builder()
                .companyId(company.getCompanyId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .companyPresidnetName(company.getPresidentName())
                .companyBizNumber(company.getBizNumber())
                .build();
    }

    public void validDuplicateCompany(BizNumberValidateRequestForm bizNumberValidateRequestForm) {
        boolean flag = companyRepository.existsByBizNumber(bizNumberValidateRequestForm.getBNo());
        if(flag){
            throw new IllegalStateException("등록된 회사입니다.");
        }
    }
}
