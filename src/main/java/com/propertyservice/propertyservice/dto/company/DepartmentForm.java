package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.domain.company.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentForm {

    private Company companyId;

    private String departmentName;

    private String departmentCode;
}
