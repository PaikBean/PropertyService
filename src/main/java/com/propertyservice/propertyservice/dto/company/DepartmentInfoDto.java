package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class DepartmentInfoDto {
    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private String departmentPresidentName;

    private Long managerId;
    private BigDecimal departmentTotalRevenue;
    private List<ManagerInfoDto> departmentManagerList;

    @QueryProjection
    public DepartmentInfoDto(Long departmentId, String departmentName, String departmentCode, String departmentPresidentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode =departmentCode;
        this.departmentPresidentName = departmentPresidentName;
    }

    @QueryProjection
    public DepartmentInfoDto(Long departmentId, String departmentName, String departmentCode, Long managerId, BigDecimal departmentTotalRevenue) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode =departmentCode;
        this.managerId = managerId;
        this.departmentTotalRevenue = departmentTotalRevenue;
    }

    @Builder
    public DepartmentInfoDto(Long departmentId, String  departmentName, String departmentCode, Long managerId, BigDecimal departmentTotalRevenue, List<ManagerInfoDto> departmentManagerList){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode =departmentCode;
        this.managerId = managerId;
        this.departmentTotalRevenue = departmentTotalRevenue;
        this.departmentManagerList = departmentManagerList;
    }
}
