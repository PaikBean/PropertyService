package com.propertyservice.propertyservice.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerStateDto {
    private Long managerStateId;
    private String managerState;

    @Builder
    public ManagerStateDto(Long managerStateId, String managerState) {
        this.managerStateId = managerStateId;
        this.managerState = managerState;
    }
}
