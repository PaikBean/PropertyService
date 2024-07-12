package com.propertyservice.propertyservice.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 json 정보를 맵핑하기 위한 Form DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginFormDto {
    private String email;
    private String password;
}
