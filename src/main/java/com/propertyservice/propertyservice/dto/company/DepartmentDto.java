package com.propertyservice.propertyservice.dto.company;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;

    private Long companyId;
    private List<DepartmentInfoDto> departmentInfoDtoList;

    @QueryProjection
    public DepartmentDto(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    @Builder
    public DepartmentDto(Long companyId, List<DepartmentInfoDto> departmentInfoDtoList){
        this.companyId =companyId;
        this.departmentInfoDtoList = departmentInfoDtoList;
    }


}
