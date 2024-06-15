package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.company.CompanyAddress;
import com.propertyservice.propertyservice.dto.company.CompanyRegistryForm;
import com.propertyservice.propertyservice.dto.company.CompanySimpleDto;
import com.propertyservice.propertyservice.dto.company.DepartmentForm;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.property.CompanyAddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DepartmentService departmentService;
    private final CompanyAddressRepository companyAddressRepository;
    public Company searchCompany(String companyCode){
        return companyRepository.findByCompanyCode(companyCode).orElseThrow(
                ()->new EntityNotFoundException("회사코드가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }

    public CompanySimpleDto searchCompanyByCompanyCode(String companyCode) {
        Company company = companyRepository.findByCompanyCode(companyCode).orElseThrow(
                () -> new EntityNotFoundException("회사코드가 존재하지 않습니다.\n 관리자에게 문의해주세요.")
        );
        return CompanySimpleDto.builder()
                .companyId(company.getCompanyId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .presidentName(company.getPresidentName())
                .businessRegistrationNumber(company.getBusinessRegistrationNumber())
                .build();
    }

    @Transactional
    public void registerCompany(CompanyRegistryForm companyRegistryForm) {
        CompanyAddress companyAddress = companyAddressRepository.save(CompanyAddress.builder()
                        .addressLevel1Id(companyRegistryForm.getCompanyAddressLevel1Id())
                        .addressLevel2Id(companyRegistryForm.getCompanyAddressLevel2Id())
                        .addressLevel3(companyRegistryForm.getCompanyAddressLevel3())
                .build());
        Company company = companyRepository.save(Company.builder()
                .companyName(companyRegistryForm.getCompanyName())
                .companyAddressId(companyAddress.getCompanyAddressId())
                .presidentName(companyRegistryForm.getPresidentName())
                .companyEmail(companyRegistryForm.getCompanyEmail())
                .businessRegistrationNumber(companyRegistryForm.getBizNumber())
                .build());

        for (DepartmentForm departmentForm : companyRegistryForm.getDepartmentFormList()) {
            departmentForm.setCompanyId(company);
            departmentService.createDepartment(departmentForm);
        }
    }
}
