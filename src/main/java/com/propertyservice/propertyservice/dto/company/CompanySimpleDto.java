package com.propertyservice.propertyservice.dto.company;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanySimpleDto {
    private Long companyId;
    private String companyCode;
    private String companyName;
    private String companyPresidnetName;
    private String companyBizNumber;

    @Builder
    public CompanySimpleDto(Long companyId, String companyCode, String companyName, String companyPresidnetName, String companyBizNumber) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyPresidnetName = companyPresidnetName;
        this.companyBizNumber = companyBizNumber;
    }
}
