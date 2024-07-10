package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.company.DepartmentDto;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<DepartmentDto> searchDepartmentListByCompanyCode(String companyCode);
}
