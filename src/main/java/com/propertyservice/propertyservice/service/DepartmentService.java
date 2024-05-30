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
        return departmentRepository.findByDepartmentName(departmentName).orElse(null);
    }
}
