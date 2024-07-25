package com.propertyservice.propertyservice.dto.manager;

import com.querydsl.core.annotations.QueryProjection;

import java.math.BigDecimal;

public class ManagerInfoDto {

    private Long managerId;
    private String departmentName;
    private String managerName;
    private String managerRank;
    private String managerPosition;
    private String managerState;
    private BigDecimal managerTotalRevenueMonth;

    @QueryProjection
    public ManagerInfoDto(Long managerId, String departmentName, String managerName, String managerRank, String managerPosition){
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
    }
    @QueryProjection
    public ManagerInfoDto(Long managerId, String  managerPosition, String managerName, String managerRank, String managerState, BigDecimal managerTotalRevenueMonth){
        this.managerId = managerId;
        this.managerPosition = managerPosition;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerState =managerState;
        this.managerTotalRevenueMonth = managerTotalRevenueMonth;
    }
}
