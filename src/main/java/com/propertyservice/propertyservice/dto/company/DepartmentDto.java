package com.propertyservice.propertyservice.dto.company;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;

    @QueryProjection
    public DepartmentDto(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
}
