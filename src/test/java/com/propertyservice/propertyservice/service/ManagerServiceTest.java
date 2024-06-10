package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest

class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Test
    @Transactional
    public void createManagerTest(){
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        for(int i =0; i < 100; i++){
//            ManagerSignUpForm managerSignUpForm = new ManagerSignUpForm();
//            managerSignUpForm.setCompanyCode("COMP001");
//            managerSignUpForm.setManagerName("TEST"+i);
//            managerSignUpForm.setManagerPhoneNumber("010-0000-"+i);
//            managerSignUpForm.setManagerAddressLevel1(1L);
//            managerSignUpForm.setManagerAddressLevel2(1L);
//            managerSignUpForm.setManagerAddressLevel3("default "+i);
//            managerSignUpForm.setGender(Gender.MALE);
//            managerSignUpForm.setDepartmentName("Sales");
//            managerSignUpForm.setManagerRank("rank default");
//            managerSignUpForm.setManagerPosition("position default");
//            managerSignUpForm.setManagerStateId(1L);
//            managerSignUpForm.setManagerCode("code default");
//            managerSignUpForm.setManagerEmail("test"+i+"@email.com");
//            managerSignUpForm.setManagerPassword("123123");
//
//            managerService.createManager(managerSignUpForm);
//        }


        for(int i =0; i <100; i++){
            ManagerSignUpForm managerSignUpForm = ManagerSignUpForm.builder()
                .companyCode("COMP001")
                .managerName("test"+i)
                .managerPhoneNumber("010-0000-0000"+i)
                .gender(Gender.MALE)
                .departmentName("Sales")
                .managerRank("사원")
                .managerPosition("개발")
                .managerStateId(1L)
                .managerCode("24080599001")
                .managerEmail("test"+i+"@test.com")
                .managerPassword("123123")

                .managerAddressLevel1(1L)
                .managerAddressLevel2(1L)
                .managerAddressLevel3("asd")
                .build();
            managerService.createManager(managerSignUpForm);
        }
    }
}