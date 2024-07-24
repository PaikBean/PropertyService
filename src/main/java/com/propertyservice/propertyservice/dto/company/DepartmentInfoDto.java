package com.propertyservice.propertyservice.dto.company;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DepartmentInfoDto {
    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private String departmentPresidentName;

    @QueryProjection
    public DepartmentInfoDto(Long departmentId, String departmentName, String departmentCode, String departmentPresidentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode =departmentCode;
        this.departmentPresidentName = departmentPresidentName;
    }
}
