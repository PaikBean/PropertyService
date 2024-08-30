package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.company.DepartmentDto;
import com.propertyservice.propertyservice.dto.company.DepartmentInfoDto;
import com.propertyservice.propertyservice.dto.company.DepartmentTotalRevenueCondition;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentRepositoryCustom {
    List<DepartmentDto> searchDepartmentListByCompanyCode(String companyCode);

    List<DepartmentInfoDto> searchDepartmentList(Long companyId);

    List<DepartmentInfoDto> searchDepartmentInfo(Long departmentId);

    List<BigDecimal> searchDepartmentTotalRevenue(DepartmentTotalRevenueCondition departmentTotalRevenueCondition);
}
