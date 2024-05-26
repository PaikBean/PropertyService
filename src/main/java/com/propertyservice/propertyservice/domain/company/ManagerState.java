package com.propertyservice.propertyservice.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "manager_state")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_state_id")
    private Long managerStateId;
    @Column(nullable = false)
    private String managerState;

    @Builder
    public ManagerState(Long managerStateId, String managerState) {
        this.managerStateId = managerStateId;
        this.managerState = managerState;
    }
}
