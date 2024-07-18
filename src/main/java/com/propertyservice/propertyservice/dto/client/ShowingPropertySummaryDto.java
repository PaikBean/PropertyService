package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ShowingPropertySummaryDto {
    private Long clientId; //고객id

    private Long propertyId; // 매물id
    private String unitNumber;  //호수
    private PropertyType propertyType;    //주용도
    private TransactionType transactionType; //거래유형
    private BigDecimal deposit; //보증금
    private BigDecimal monthlyFee;  //월세
    private BigDecimal jeonseFee;   //전세
    private BigDecimal tradeFee;    //매매금

    private Long remarkId; //매물특이사항 id
    private String remark; //매물 특이사항.

    @QueryProjection
    public ShowingPropertySummaryDto(Long clientId, Long propertyId, String unitNumber, PropertyType propertyType, TransactionType transactionType, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, Long remarkId, String remark){
        this.clientId = clientId;
        this.propertyId = propertyId;
        this.unitNumber = unitNumber;
        this.propertyType =propertyType;
        this.transactionType = transactionType;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee =jeonseFee;
        this.tradeFee = tradeFee;
        this.remarkId =remarkId;
        this.remark     = remark;
    }
}
