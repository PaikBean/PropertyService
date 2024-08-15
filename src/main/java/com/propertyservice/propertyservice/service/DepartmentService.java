package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.company.*;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
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
    private final EntityExceptionService entityExceptionService;
    private final CommonService commonService;
    private final ManagerService managerService;
    private final ManagerRepository managerRepository;

    /**
     * 부서 등록.
     */
    public Long createDepartment(DepartmentForm departmentForm){
        return departmentRepository.save(Department.builder()
                .company(departmentForm.getCompany())
                .departmentName(departmentForm.getDepartmentName())
                .departmentCode(departmentForm.getDepartmentCode())
                .departmentPresidentName(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findByManagerId(departmentForm.getManagerId()),
                                "회원이 존재하지 않습니다. 관리자에게 문의해주세요."
                        )
                )
                .build()).getDepartmentId();
    }

    /**
     * 회사 부서 목록 조회. ( api 미지정 )
     */
    public List<DepartmentDto> searchDepartmentList(String companyCode) {
        return departmentRepository.searchDepartmentListByCompanyCode(companyCode);
    }

    /**
     * SecurityContentHolder 버전
     * 현재 로그인한 사용자 부서 목록 조회.
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
     * companyId 버전
     * 현재 로그인한 사용자 부서 목록 조회.
     */
    public DepartmentDto searchDepartmentList(Long companyId){
        // 회사로 목록 조회.
        return DepartmentDto.builder()
                .companyId(companyId)
                .departmentInfoDtoList(departmentRepository.searchDepartmentList(companyId))
                .build();
    }


    /**
     * 부서Id로 부서 상세 정보 조회.
     */
    public DepartmentInfoDto searchDepartmentInfo(Long departmentId){
//        departmentRepository.findByDepartmentId(departmentId).orElseThrow(
//                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요.")
//        );
        entityExceptionService.validateEntityExists(
                () -> departmentRepository.findByDepartmentId(departmentId),
                "부서가 존재하지 않습니다. 관리자에게 문의해주세요."
        );
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
    }

    /**
     * 부서 목록 조회.
     */
    public List<ManagerInfoDto> searchManagerInfoListByDepartmentId(Long departmentId){
//        departmentRepository.findByDepartmentId(departmentId).orElseThrow(
//                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요.")
//        );
        entityExceptionService.validateEntityExists(
                () -> departmentRepository.findByDepartmentId(departmentId),
                "부서가 존재하지 않습니다. 관리자에게 문의해주세요."
        );
        return managerService.searchManagerInfoListByDepartmentId(departmentId);
    }


    /**
     * 부서 총 매출액 조회.
     */
    public BigDecimal searchDepartmentTotalRevenue(DepartmentTotalRevenueCondition departmentTotalRevenueCondition){
//        departmentRepository.findByDepartmentId(departmentTotalRevenueCondition.getDepartmentId()).orElseThrow(
//                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요.")
//        );
        entityExceptionService.validateEntityExists(
                () -> departmentRepository.findByDepartmentId(departmentTotalRevenueCondition.getDepartmentId()),
                "부서가 존재하지 않습니다. 관리자에게 문의해주세요."
        );
        return departmentRepository.searchDepartmentTotalRevenue(departmentTotalRevenueCondition).get(0);
    }


    /**
     * 부서 정보 수정.
     */
    @Transactional
    public Long updateDepartmentInfo(DepartmentInfoForm departmentInfoForm){
        Department department = entityExceptionService.findEntityById(
                () -> departmentRepository.findByDepartmentId(departmentInfoForm.getDepartmentId()),
                "부서가 존재하지 않습니다. 관리자에게 문의해주세요."
        );

        if(departmentInfoForm.getDepartmentId() != null) {
            Manager departmentPresidentName = entityExceptionService.findEntityById(
                    () -> managerRepository.findByManagerId(departmentInfoForm.getManagerId()),
                    "회원이 존재하지 않습니다. 관리자에게 문의해주세요."
            );

            department.updateDepartment(departmentInfoForm, departmentPresidentName);
        }
        else
            department.updateDepartment(departmentInfoForm, department.getDepartmentPresidentName());

        return departmentRepository.save(department).getDepartmentId();
    }


    /**
     * 부서 삭제.
     */
    @Transactional
    public void deleteDepartment(Long departmentId){
        departmentRepository.delete(entityExceptionService.findEntityById(
                () -> departmentRepository.findByDepartmentId(departmentId),
                "부서가 존재하지 않습니다. 관리자에게 문의해주세요."
        )
        );
    }
}
