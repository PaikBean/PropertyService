package com.propertyservice.propertyservice.domain.manager;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum ManagerState {
    EMPLOYMENT("재직"),
    LEAVE("휴직"),
    RESIGNATION("퇴사");

    private final String label;

    ManagerState(String label){
        this.label = label;
    }
}
