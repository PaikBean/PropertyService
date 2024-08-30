package com.propertyservice.propertyservice.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentInfoForm {
    private Long departmentId;
    private String departmentName;
    private Long managerId;
}
