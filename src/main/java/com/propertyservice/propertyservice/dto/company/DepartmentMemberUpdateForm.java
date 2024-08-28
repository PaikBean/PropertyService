package com.propertyservice.propertyservice.dto.company;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DepartmentMemberUpdateForm {
    private Long departmentId;
    private List<Long> managerIdList;
}
