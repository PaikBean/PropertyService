package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.manager.ManagerState;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.dto.manager.ManagerSignUpForm;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagerRepository managerRepository;

    @Test
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


        for(int i =0; i <2; i++){
            ManagerSignUpForm managerSignUpForm = ManagerSignUpForm.builder()
                .companyCode("COMP001")
                .managerName("test"+i)
                .managerPhoneNumber("010-0000-0000"+i)
                .gender(Gender.MALE)
//                .departmentName("Sales")
                    .departmentId(1L)
                .managerRank("사원")
                .managerPosition("개발")
                .managerState(ManagerState.EMPLOYMENT)
                .managerCode("24080599001")
                .managerEmail("test"+i+"@test.com")
                .managerPassword("123123")

//                .managerAddressLevel1(1L)
//                .managerAddressLevel2(1L)
//                .managerAddressLevel3("asd")
                .build();
            managerService.createManager(managerSignUpForm);
        }
    }

    @Test
    public void resetPasswordTest(){
        String managerEmail = "test0@test.com";
        String companyCode = "COMP001";

        String password = managerService.searchPassword(managerEmail, companyCode);
        System.out.println("Reset Password : " + password);
    }

    @Test
    public void searchManagerList(){
        System.out.println(managerService.searchManagerList(1L));
    }

    @Test
    public void searchManagerInfoListByDepartmentIdTest(){
        List<ManagerInfoDto> managerInfoDtoList = managerRepository.searchManagerInfoListByDepartmentId(1L);
        System.out.println(managerInfoDtoList.get(0).getManagerTotalRevenueMonth());
    }

    @Test
    public void searchManagerInfo(){
        ManagerInfoDto managerInfoDto = managerService.searchManagerInfo(1L);
        System.out.println(managerInfoDto.getManagerPicClient());
        System.out.println(managerInfoDto.getManagerTotalRevenueMonth());
        System.out.println(managerInfoDto.getManagerTotalRevenue());
    }
}