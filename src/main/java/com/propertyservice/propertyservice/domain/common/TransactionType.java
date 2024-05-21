package com.propertyservice.propertyservice.domain.common;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Entity
@Getter
@Table(name = "transaction_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private Long transactionTypeId;
    @Column(nullable = false)
    private String transactionTypeName;
    @Column(nullable = false)
    private String transactionTypeCode;
    @Builder.Default
    @ColumnDefault("true")
    @Column(nullable = false)
    private Boolean isUsed = true;

    @Builder
    public TransactionType(Long transactionTypeId, String transactionTypeName, String transactionTypeCode, Boolean isUsed) {
        this.transactionTypeId = transactionTypeId;
        this.transactionTypeName = transactionTypeName;
        this.transactionTypeCode = transactionTypeCode;
        this.isUsed = isUsed;
    }
}
