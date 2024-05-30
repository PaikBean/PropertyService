package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.dto.company.ManagerForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ManagerServiceTest {
    @Autowired
    private ManagerService managerService;
    @Test
    public void createManagerTest(){
        ManagerForm managerForm = ManagerForm.builder()
                .companyCode("COMP001")
                .managerName("test")
                .managerPhoneNumber("010-0000-0000")
                .gender(Gender.MALE)
                .departmentName("Sales")
                .managerRank("사원")
                .managerPosition("개발")
                .state("휴가")
                .managerCode("24080599001")
                .managerEmail("test@test.com")
                .managerPassword("123123")

                .managerAddressLevel1(1L)
                .managerAddressLevel2(1L)
                .managerAddressLevel3("asd")
                .build();

        managerService.createManager(managerForm);

    }

}