package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.dto.company.CompanySimpleDto;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
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
    private final ManagerRepository managerRepository;
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
                .presidentName(managerRepository.findById(company.getPresident_id()).orElseThrow(
                        () -> new EntityNotFoundException("대표자가 등록되지 않았습니다.")
                ).getManagerName())
                .businessRegistrationNumber(company.getBusinessRegistrationNumber())
                .build();
    }
}
