package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.company.CompanyInfoForm;
import com.propertyservice.propertyservice.dto.company.CompanyManagerDto;
import com.propertyservice.propertyservice.dto.company.CompanyRegistryForm;
import com.propertyservice.propertyservice.dto.company.CompanySimpleDto;
import com.propertyservice.propertyservice.dto.manager.ManagerSignUpForm;
import com.propertyservice.propertyservice.dto.manager.ManagerSimpleInfoDto;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ManagerRepository managerRepository;
    private final CommonService commonService;
    private final EntityExceptionService entityExceptionService;

    public Company searchCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode).orElseThrow(
                () -> new EntityNotFoundException("회사코드가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }

    public Company searchCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new EntityNotFoundException("회사가 존재하지 않습니다.")
        );
    }

    /**
     * 회사 등록.
     */
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

    /**
     * 회사 요약 정보 조회.
     */
    public CompanySimpleDto validateCompanyCode(String companyCode) {
        Company company = entityExceptionService.findEntityById(
                () -> companyRepository.findByCompanyCode(companyCode),
                "회사 정보가 존재하지 않습니다. 관리자에게 문의하세요"
        );
        return CompanySimpleDto.builder()
                .companyId(company.getCompanyId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .companyPresidnetName(company.getPresidentName())
                .companyBizNumber(company.getBizNumber())
                .build();
    }

    /**
     *  회사 중복 조회.
     */
    public void validDuplicateCompany(BizNumberValidateRequestForm bizNumberValidateRequestForm) {
        boolean flag = companyRepository.existsByBizNumber(bizNumberValidateRequestForm.getBNo());
        if(flag){
            throw new IllegalStateException("등록된 회사입니다.");
        }
    }

    /**
     * 회사 직원 목록 상세 조회.
     */
    public CompanyManagerDto searchManagerListForCompanyId(Long companyId){
        return CompanyManagerDto.builder()
                .companyId(companyId)
                .managerInfoDtoList(managerRepository.searchManagerInfoListByCompanyId(companyId))
                .build();
    }

    /**
     * 회사 직원 목록 요약 조회.
     * @return
     */
    public List<ManagerSimpleInfoDto> searchManagerList() {
        List<ManagerSimpleInfoDto> managerSimpleInfoDtoList = new ArrayList<>();
        for (Manager manager : managerRepository.findAllByCompany(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())) {
            managerSimpleInfoDtoList.add(ManagerSimpleInfoDto.builder()
                            .managerId(manager.getManagerId())
                            .managerName(manager.getManagerName())
                    .build());
        }
        return managerSimpleInfoDtoList;
    }
}
