package com.propertyservice.propertyservice.dto.manager;

import com.querydsl.core.annotations.QueryProjection;

public class ManagerInfoDto {

    private Long managerId;
    private String departmentName;
    private String managerName;
    private String managerRank;
    private String managerPosition;

    @QueryProjection
    public ManagerInfoDto(Long managerId, String departmentName, String managerName, String managerRank, String managerPosition){
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
    }
}
