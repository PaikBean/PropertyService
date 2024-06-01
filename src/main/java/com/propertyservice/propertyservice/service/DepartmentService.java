package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    public Department searchDepartment(String departmentName){
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(
                ()-> new IllegalStateException("부서가 존재하지 않습니다.\n 관리자에게 문의해주세요."));
    }
}
