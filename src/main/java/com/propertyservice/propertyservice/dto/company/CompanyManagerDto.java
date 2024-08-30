package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CompanyManagerDto {
    private Long companyId;
    private List<ManagerInfoDto> managerInfoDtoList;

    @Builder
    public CompanyManagerDto(Long companyId, List<ManagerInfoDto> managerInfoDtoList){
        this.companyId = companyId;
        this.managerInfoDtoList = managerInfoDtoList;
    }
}
