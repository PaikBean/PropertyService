package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.domain.common.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ManagerSignUpForm {
    private String companyCode;
    private String managerName;
    private String managerPhoneNumber;
    private Gender gender;
//    private String departmentName;        회원가입시에 부서 등록을 '부서 이름'이 아닌 '부서 Id'로 함
    private Long departmentId;
    private String managerRank;
    private String managerPosition;
    private Long managerStateId;
    private String managerCode;
    private String managerEmail;
    private String managerPassword;

    @Builder
    public ManagerSignUpForm(String companyCode, String managerName, String managerPhoneNumber, Gender gender, Long departmentId, String managerRank, String managerPosition, Long managerStateId, String managerCode, String managerEmail, String managerPassword) {
        this.companyCode = companyCode;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.gender = gender;
        this.departmentId = departmentId;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.managerStateId = managerStateId;
        this.managerCode = managerCode;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
    }
}
