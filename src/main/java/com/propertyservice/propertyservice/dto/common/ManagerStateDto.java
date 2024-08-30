package com.propertyservice.propertyservice.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerStateDto {
    private String managerStateName;
    private String label;

    @Builder
    public ManagerStateDto(String managerStateName, String label) {
        this.managerStateName = managerStateName;
        this.label = label;
    }
}
