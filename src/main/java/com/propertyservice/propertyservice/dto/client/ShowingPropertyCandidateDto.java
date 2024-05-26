package com.propertyservice.propertyservice.dto.client;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ShowingPropertyCandidateDto {
    private Long propertyId;
    private Long transactionType;
    private String ownerName;
    private String address;
    @Setter
    private String price;

    @QueryProjection
    public ShowingPropertyCandidateDto(Long propertyId, Long transactionType, String ownerName, String address) {
        this.propertyId = propertyId;
        this.transactionType = transactionType;
        this.ownerName = ownerName;
        this.address = address;
    }
}
