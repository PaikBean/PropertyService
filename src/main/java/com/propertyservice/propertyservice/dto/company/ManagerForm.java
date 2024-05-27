package com.propertyservice.propertyservice.dto.company;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerForm {

    private String companyCode; //회사 코드
    private String managerEmail;
    private String managerPassword;
    private String managerName;

    @Builder
    public ManagerForm(String companyCode, String managerEmail, String managerPassword, String managerName){
        this.companyCode = companyCode;
        this.managerEmail = managerEmail;
        this.managerPassword =managerPassword;
        this.managerName = managerName;
    }

}
