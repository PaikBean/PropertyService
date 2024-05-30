package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.domain.common.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerForm {
    private String companyCode; // 회사코드. 코드가 존재하면 다음 (Stepper 확인)

    // information method
    private String managerName; // 이름
    private String managerPhoneNumber; // 핸드폰 번호.
    private Long managerAddressLevel1;
    private Long managerAddressLevel2;
    private String managerAddressLevel3;
    private Gender gender; // 성별

    // 가입 기본정보.
    private String departmentName; // 부서명.
    private String managerRank; // 직급
    private String managerPosition; // 직무
    private String state; // 상태  default: 근무, 그 외 휴가, 병가 등
    private String managerCode; // 사번 (가입년도 월일년도숫자.)
                                /*
                                    ex) 24080599001
                                        24032200002 식으로 설정?
                                */

    private String managerEmail; // 이메일
    private String managerPassword; // 비밀번호.

    @Builder
    public ManagerForm(Long managerAddressLevel1, Long managerAddressLevel2, String managerAddressLevel3, String companyCode,String managerRank, String managerPosition, String state, String managerCode,  String managerEmail, String managerPassword, String managerName, String managerPhoneNumber, Gender gender, String departmentName){
        this.companyCode = companyCode;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.gender =gender;
        this.departmentName =departmentName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.state = state;
        this.managerCode = managerCode;
        this.managerEmail = managerEmail;
        this.managerPassword =managerPassword;

        this.managerAddressLevel1 = managerAddressLevel1;
        this.managerAddressLevel2 = managerAddressLevel2;
        this.managerAddressLevel3 =managerAddressLevel3;


    }

}
