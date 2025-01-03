package com.propertyservice.propertyservice.domain.company;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.common.Role;
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
    private Company company_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department_id; // department Entity
    private String managerName;
    private String managerRank;
    private String managerPosition;
    private String managerCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_state_id" )
    private ManagerState managerStateId; //stateId Entity
    private Gender gender; // genderId;
    private String managerPhoneNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_address_id" )
    private ManagerAddress managerAddressId; // addressId Entity

    private LocalDateTime managerEntranceDate;
    private LocalDateTime managerResignDate;
    private String managerEmail; // Login Email;
    private String managerPassword;
    private Integer passwordErrorCount;
    private Role role;


    @Builder
    public Manager(Company company_id, Department department_id, String managerName, String managerRank, String managerPosition, String managerCode, ManagerState managerStateId, Gender gender, String managerPhoneNumber, ManagerAddress managerAddressId, LocalDateTime managerEntranceDate, LocalDateTime managerResignDate, String managerEmail, String managerPassword, Integer passwordErrorCount, Role role){
        this.company_id = company_id;
        this.department_id = department_id;
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.managerCode = managerCode;
        this.managerStateId = managerStateId;
        this.gender = gender;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerAddressId = managerAddressId;
        this.managerEntranceDate = managerEntranceDate;
        this.managerResignDate = managerResignDate;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
        this.passwordErrorCount =passwordErrorCount;
        this.role = role;
    }

}
