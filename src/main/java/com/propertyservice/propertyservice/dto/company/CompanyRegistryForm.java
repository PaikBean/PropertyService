package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.dto.manager.ManagerSignUpForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRegistryForm {
    private CompanyInfoForm companyInfo;
    private ManagerSignUpForm presidentInfo;
}
