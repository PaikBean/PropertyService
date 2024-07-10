package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.dto.company.DepartmentDto;
import com.propertyservice.propertyservice.dto.company.DepartmentForm;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    public Department searchDepartment(String departmentName){
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(
                ()-> new EntityNotFoundException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }

    /**
     * 부서 등록.
     * @param departmentForm
     * @return
     */
    public Long createDepartment(DepartmentForm departmentForm){
        return departmentRepository.save(Department.builder()
                .companyId(departmentForm.getCompanyId())
                .departmentName(departmentForm.getDepartmentName())
                .departmentCode(departmentForm.getDepartmentCode())
                .build()).getDepartmentId();
    }

    public List<DepartmentDto> searchDepartmentList(String companyCode) {
        return departmentRepository.searchDepartmentListByCompanyCode(companyCode);
    }
}
