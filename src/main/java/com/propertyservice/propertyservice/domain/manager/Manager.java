package com.propertyservice.propertyservice.domain.manager;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.common.Role;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "manager")
@Entity
public class Manager extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long managerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department; // department Entity
    private String managerName;
    private String managerRank;
    private String managerPosition;
    private String managerCode;

    private ManagerState managerState; //stateId Entity
    private Gender gender; // genderId;
    private String managerPhoneNumber;

    private LocalDateTime managerEntranceDate;
    private LocalDateTime managerResignDate;
    private String managerEmail; // Login Email;
    private String managerPassword;
    private Integer passwordErrorCount;
    private Role role;


    @Builder
    public Manager(Long managerId, Company company, Department department, String managerName, String managerRank, String managerPosition, String managerCode, ManagerState managerState, Gender gender, String managerPhoneNumber, LocalDateTime managerEntranceDate, LocalDateTime managerResignDate, String managerEmail, String managerPassword, Integer passwordErrorCount, Role role) {
        this.managerId = managerId;
        this.company = company;
        this.department = department;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.managerCode = managerCode;
        this.managerState = managerState;
        this.gender = gender;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerEntranceDate = managerEntranceDate;
        this.managerResignDate = managerResignDate;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
        this.passwordErrorCount = passwordErrorCount;
        this.role = role;
    }

    public void resetPassword(String managerPassword){
        this.managerPassword = managerPassword;
    }

    public void updateManagerInfo(ManagerInfoForm managerInfoForm){
        this.managerName = managerInfoForm.getManagerName();
        this.gender = Gender.valueOf(managerInfoForm.getManagerGender());
        this.managerPhoneNumber = managerInfoForm.getManagerPhoneNumber();
        this.managerPosition = managerInfoForm.getManagerPosition();
        this.managerRank = managerInfoForm.getManagerRank();
    }
}
