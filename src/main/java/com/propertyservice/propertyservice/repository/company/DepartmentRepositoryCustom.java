package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.company.DepartmentDto;
import com.propertyservice.propertyservice.dto.company.DepartmentInfoDto;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<DepartmentDto> searchDepartmentListByCompanyCode(String companyCode);

    List<DepartmentInfoDto> searchDepartmentList(Long companyId);

    List<DepartmentInfoDto> searchDepartmentInfo(Long departmentId);
}
