package com.propertyservice.propertyservice.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerInfoForm {
    private Long managerId;
    private String managerName;
    private String managerGender;
    private String managerPhoneNumber;
    private String managerPosition;
    private String managerRank;
}
