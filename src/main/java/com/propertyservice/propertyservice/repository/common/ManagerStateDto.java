package com.propertyservice.propertyservice.repository.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerStateDto {
    private Long mangerStateId;
    private String managerState;

    @Builder
    public ManagerStateDto(Long mangerStateId, String managerState) {
        this.mangerStateId = mangerStateId;
        this.managerState = managerState;
    }
}
