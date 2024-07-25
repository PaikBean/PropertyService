package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;

import java.util.List;

public interface ManagerRepositoryCustom {
    List<ManagerInfoDto> searchManagerInfoList(Long companyId);
}
