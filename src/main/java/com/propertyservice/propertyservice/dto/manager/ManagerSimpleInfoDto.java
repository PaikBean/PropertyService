package com.propertyservice.propertyservice.dto.manager;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerSimpleInfoDto {
    private Long managerId;
    private String managerName;

    @Builder
    public ManagerSimpleInfoDto(Long managerId, String managerName) {
        this.managerId = managerId;
        this.managerName = managerName;
    }
}
