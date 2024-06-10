package com.propertyservice.propertyservice.dto.company;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanySimpleDto {
    private Long companyId;
    private String companyCode;
    private String companyName;
    private String presidentName;
    private String businessRegistrationNumber;

    @Builder
    public CompanySimpleDto(Long companyId, String companyCode, String companyName, String presidentName, String businessRegistrationNumber) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.presidentName = presidentName;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }
}
