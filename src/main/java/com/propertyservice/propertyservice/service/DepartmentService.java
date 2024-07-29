package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.company.*;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CommonService commonService;
    private final ManagerService managerService;
    public Department searchDepartmentByDepartmentName(String departmentName){
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(
                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }
    public Department searchDepartmentByDepartmentId(Long departmentId){
        return departmentRepository.findByDepartmentId(departmentId).orElseThrow(
                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }

    /**
     * 부서 등록.
     * @param departmentForm
     * @return
     */
    public Long createDepartment(DepartmentForm departmentForm){
        return departmentRepository.save(Department.builder()
                .company(departmentForm.getCompany())
                .departmentName(departmentForm.getDepartmentName())
                .departmentCode(departmentForm.getDepartmentCode())
                .departmentPresidentName(managerService.searchManagerById(departmentForm.getManagerId()))
                .build()).getDepartmentId();
    }

    public List<DepartmentDto> searchDepartmentList(String companyCode) {
        return departmentRepository.searchDepartmentListByCompanyCode(companyCode);
    }

    /**
     * SecurityContentHolder 버전
     * @return
     */
    public DepartmentDto searchDepartmentList(){
        CustomUserDetail customUserDetail = commonService.getCustomUserDetailBySecurityContextHolder();
        Company company = customUserDetail.getCompany();

        // 회사로 목록 조회.
        return DepartmentDto.builder()
                .companyId(company.getCompanyId())
                .departmentInfoDtoList(departmentRepository.searchDepartmentList(company.getCompanyId()))
                .build();
    }

    /**
     * companyId
     * @return
     */
    public DepartmentDto searchDepartmentList(Long companyId){
        // 회사로 목록 조회.
        return DepartmentDto.builder()
                .companyId(companyId)
                .departmentInfoDtoList(departmentRepository.searchDepartmentList(companyId))
                .build();
    }


    public DepartmentInfoDto searchDepartmentInfo(Long departmentId){
        DepartmentInfoDto departmentInfoDtoByRepository = departmentRepository.searchDepartmentInfo(departmentId).get(0);
        List<ManagerInfoDto> deparmentManagerList = searchManagerInfoListByDepartmentId(departmentId);

        return DepartmentInfoDto.builder()
                .departmentId(departmentId)
                .departmentName(departmentInfoDtoByRepository.getDepartmentName())
                .departmentCode(departmentInfoDtoByRepository.getDepartmentCode())
                .managerId(departmentInfoDtoByRepository.getManagerId())
                .departmentTotalRevenue(departmentInfoDtoByRepository.getDepartmentTotalRevenue())
                .departmentManagerList(deparmentManagerList)
                .build();

//        return null;
    }

    /**
     * 부서 목록 조회.
     * @param departmentId
     * @return
     */
    public List<ManagerInfoDto> searchManagerInfoListByDepartmentId(Long departmentId){
        return managerService.searchManagerInfoListByDepartmentId(departmentId);
    }

    public BigDecimal searchDepartmentTotalRevenue(DepartmentTotalRevenueCondition departmentTotalRevenueCondition){
        return departmentRepository.searchDepartmentTotalRevenue(departmentTotalRevenueCondition).get(0);
    }


    /**
     * 부서 정보 수정.
     * @param departmentInfoForm
     * @return
     */
    @Transactional
    public Long updateDepartmentInfo(DepartmentInfoForm departmentInfoForm){
        Department department = searchDepartmentByDepartmentId(departmentInfoForm.getDepartmentId());
        if(departmentInfoForm.getDepartmentId() != null) {
            Manager departmentPresidentName = managerService.searchManagerById(departmentInfoForm.getManagerId());
            department.updateDepartment(departmentInfoForm, departmentPresidentName);
        }
        else
            department.updateDepartment(departmentInfoForm, department.getDepartmentPresidentName());

        return departmentRepository.save(department).getDepartmentId();
    }
    @Transactional
    public void deleteDepartment(Long departmentId){
        departmentRepository.delete(searchDepartmentByDepartmentId(departmentId));
    }
}
