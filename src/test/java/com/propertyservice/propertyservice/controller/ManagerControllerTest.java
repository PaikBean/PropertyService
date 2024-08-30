package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.manager.ManagerState;
import com.propertyservice.propertyservice.dto.manager.ManagerSignUpForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagerControllerTest {

    @Autowired
    private ManagerController managerController;

    @Test
    public void createManagerTest(){
        ManagerSignUpForm managerSignUpForm = ManagerSignUpForm.builder()
                .companyCode("COMP001")
                .managerName("test")
                .managerPhoneNumber("010-0000-0000")
                .gender(Gender.MALE)
//                .departmentName("Sales")
                .departmentId(null)
                .managerRank("사원")
                .managerPosition("개발")
                .managerState(ManagerState.EMPLOYMENT)
                .managerCode("24080599001")
                .managerEmail("test@test.com")
                .managerPassword("123123")
//                .managerAddressLevel1(1L)
//                .managerAddressLevel2(1L)
//                .managerAddressLevel3("asd")
                .build();

        System.out.println("Controller CreateManager : " + managerController.createManager(managerSignUpForm).getResponseCode());

    }
    @Test
    public void checkEmail(){
        System.out.println("Controller checkEmail" + managerController.checkEmail("1@1").getResponseCode());
    }

    @Test
    public void checkEmail2(){
        System.out.println("Controller checkEmail" + managerController.checkChange("1@1").getResponseCode());
    }


}