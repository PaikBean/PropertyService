package com.propertyservice.propertyservice.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyRegistryForm {
    @NotNull @NotBlank
    private String bizNumber;
    @NotNull @NotBlank
    private String companyName;
    private Long companyAddressLevel1Id;
    private Long companyAddressLevel2Id;
    private String companyAddressLevel3;
    @NotNull @NotBlank
    private String presidentName;
    @NotNull @NotBlank
    private String companyEmail;
    private String serviceStartDate;
    private String serviceEndDate;

    private List<DepartmentForm> departmentFormList;
}
