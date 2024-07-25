package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerRepositoryCustom {
    List<ManagerInfoDto> searchManagerInfoListByCompanyId(Long companyId);

    List<ManagerInfoDto> searchManagerInfoListByDepartmentId(Long departmentId);

    List<BigDecimal> managerTotalRevenue(Long managerId);
}
