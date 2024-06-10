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
    private Long managerAddressLevel1;
    private Long managerAddressLevel2;
    private String managerAddressLevel3;
    private Gender gender;
    private String departmentName;
    private String managerRank;
    private String managerPosition;
    private Long managerStateId;
    private String managerCode;
    private String managerEmail;
    private String managerPassword;

    @Builder
    public ManagerSignUpForm(String companyCode ,String managerName, String managerPhoneNumber, Long managerAddressLevel1, Long managerAddressLevel2, String managerAddressLevel3, Gender gender, String departmentName, String managerRank, String managerPosition, Long managerStateId, String managerCode, String managerEmail, String managerPassword) {
        this.companyCode = companyCode;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerAddressLevel1 = managerAddressLevel1;
        this.managerAddressLevel2 = managerAddressLevel2;
        this.managerAddressLevel3 = managerAddressLevel3;
        this.gender = gender;
        this.departmentName = departmentName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.managerStateId = managerStateId;
        this.managerCode = managerCode;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
    }
}
