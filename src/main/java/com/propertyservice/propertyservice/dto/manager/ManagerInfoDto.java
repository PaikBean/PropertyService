package com.propertyservice.propertyservice.dto.manager;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ManagerInfoDto {

    private Long managerId;
    private String departmentName;
    private String managerName;
    private String managerRank;
    private String managerPosition;
    private String managerState;
    private BigDecimal managerTotalRevenueMonth;

    private String managerGender;
    private String managerEmail;
    private String managerPhoneNumber;
    private String companyName;
    private BigDecimal managerPicProperty;
    private BigDecimal managerPicClient;
    private BigDecimal managerTotalRevenue;

    @QueryProjection
    public ManagerInfoDto(Long managerId, String departmentName, String managerName, String managerRank, String managerPosition){
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
    }

    /**
     * 부서 매니저 리스트
     */
    @QueryProjection
    public ManagerInfoDto(Long managerId, String  managerPosition, String managerName, String managerRank, String managerState, BigDecimal managerTotalRevenueMonth){
        this.managerId = managerId;
        this.managerPosition = managerPosition;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerState =managerState;
        this.managerTotalRevenueMonth = managerTotalRevenueMonth;
    }

    @Builder
    public ManagerInfoDto(Long managerId, String managerName, String managerGender, String managerPhoneNumber, String managerEmail, String  companyName, String departmentName, String managerPosition, String managerRank, BigDecimal managerPicProperty, BigDecimal managerPicClient, BigDecimal managerTotalRevenueMonth, BigDecimal managerTotalRevenue){
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerGender = managerGender;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerEmail = managerEmail;
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.managerPosition = managerPosition;
        this.managerRank = managerRank;
        this.managerPicProperty = managerPicProperty;
        this.managerPicClient = managerPicClient;
        this.managerTotalRevenueMonth = managerTotalRevenueMonth;
        this.managerTotalRevenue = managerTotalRevenue;
    }
}
