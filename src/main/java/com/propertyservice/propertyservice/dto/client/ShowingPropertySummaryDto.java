package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.property.PropertyType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ShowingPropertySummaryDto {
    private Long propertyId; // 매물id
    private TransactionType transactionType; //거래유형
    private String address;
    private String price;


    @QueryProjection
    public ShowingPropertySummaryDto(Long propertyId,  TransactionType transactionType, String address, String price){
        this.propertyId = propertyId;
        this.transactionType = transactionType;
        this.address = address;
        this.price = price;

    }



}
