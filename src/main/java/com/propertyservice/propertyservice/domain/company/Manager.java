package com.propertyservice.propertyservice.domain.company;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mamager")
@Entity
public class Manager extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "manager_id")
    private Long managerId;
    private Long department_id; // department Entity
    private String managerName;
    private String managerRank;
    private String managerPosition;
    private String managerCode;

    private Long managerStateId; //stateId Entity
    private Long genderId; // genderId;
    private String managerPhoneNumber;

    private Long managerAddressId; // addressId Entity

    private LocalDateTime managerEntranceDate;
    private LocalDateTime managerResignDate;
    private String managerEmail; // Login Email;
    private String managerPassword;
    private Integer passwordErrorCount;


    @Builder
    public Manager(String managerName, String managerRank, String managerPosition, String managerCode, Long managerStateId, Long genderId, String managerPhoneNumber, Long managerAddressId, LocalDateTime managerEntranceDate, LocalDateTime managerResignDate, String managerEmail, String managerPassword, Integer passwordErrorCount){
        this.managerName = managerName;
        this.managerRank = managerRank;
        this.managerPosition = managerPosition;
        this.managerCode = managerCode;
        this.managerStateId = managerStateId;
        this.genderId = genderId;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerAddressId = managerAddressId;
        this.managerEntranceDate = managerEntranceDate;
        this.managerResignDate = managerResignDate;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
        this.passwordErrorCount =passwordErrorCount;
    }

}
