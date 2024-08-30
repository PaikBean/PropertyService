package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.dto.company.DepartmentInfoDto;
import com.propertyservice.propertyservice.dto.company.DepartmentTotalRevenueCondition;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void asd(){
        List<DepartmentInfoDto> departmentInfoDtoList = departmentRepository.searchDepartmentInfo(1L);
        System.out.println(departmentInfoDtoList.get(0).getDepartmentTotalRevenue());
    }

    @Test
    public void searchDepartmentTotalRevenue(){
        DepartmentTotalRevenueCondition departmentTotalRevenueCondition = new DepartmentTotalRevenueCondition();
        departmentTotalRevenueCondition.setDepartmentId(1L);
        departmentTotalRevenueCondition.setStartDate("20240720");
        departmentTotalRevenueCondition.setEndDate("20240801");
        List<BigDecimal> decimals = departmentRepository.searchDepartmentTotalRevenue(departmentTotalRevenueCondition);
        System.out.println(decimals.get(0));
    }
}