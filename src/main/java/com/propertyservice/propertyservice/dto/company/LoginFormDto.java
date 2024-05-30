package com.propertyservice.propertyservice.dto.company;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginFormDto {
    // 혹시 몰라서 작성함.
    // security 작동안 할 경우 사용.

    private String managerEmail;
    private String managerPassword;

    @Builder
    public LoginFormDto(String managerEmail, String managerPassword){
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
    }

}
