package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.dto.company.DepartmentForm;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j

class DepartmentControllerTest {
    @Autowired
    private DepartmentController departmentController;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    public DepartmentForm createForm(){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setCompanyId(companyRepository.findById(1L).orElseThrow( () -> new EntityNotFoundException("데이터가 존재 하지 않습니다.")));
        departmentForm.setDepartmentName("부서test001");
        departmentForm.setDepartmentCode("DEP001 TEST");
        return departmentForm;
    }
    @Test
    public void createDepartmentTest(){
        departmentController.createDepartment(createForm());
    }

}