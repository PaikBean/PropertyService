package com.propertyservice.propertyservice.domain.common;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@Table(name = "transaction_state")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_state_id")
    private Long transactionStateId;
    @Column(nullable = false)
    private String transactionState;

    @Builder
    public TransactionState(Long transactionStateId, String transactionState) {
        this.transactionStateId = transactionStateId;
        this.transactionState = transactionState;
    }
}
