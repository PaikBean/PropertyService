package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ShowingPropertyCandidateDto {
    private Long propertyId;
    private TransactionType transactionType;
    private String ownerName;
    private String address;
    @Setter
    private String price;

    @QueryProjection
    public ShowingPropertyCandidateDto(Long propertyId, TransactionType transactionType, String ownerName, String address) {
        this.propertyId = propertyId;
        this.transactionType = transactionType;
        this.ownerName = ownerName;
        this.address = address;
    }
}
