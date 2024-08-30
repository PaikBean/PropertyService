package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.querydsl.core.Tuple;

import java.math.BigDecimal;
import java.util.List;

public interface ManagerRepositoryCustom {
    List<ManagerInfoDto> searchManagerInfoListByCompanyId(Long companyId);

    List<ManagerInfoDto> searchManagerInfoListByDepartmentId(Long departmentId);

    BigDecimal managerTotalRevenue(Long managerId);

    BigDecimal managerTotalRevenueMonth(Long managerId);

    BigDecimal managerPicProperty(Long managerId);

    BigDecimal managerPicClient(Long managerId);

}
