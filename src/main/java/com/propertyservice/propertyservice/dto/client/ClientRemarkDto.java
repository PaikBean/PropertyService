package com.propertyservice.propertyservice.dto.client;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClientRemarkDto {
    private String managerName;
    private Long clientRemarkId;
    private Long clientId;
    private String remark;
    private LocalDateTime createdDate; //등록일.

    public ClientRemarkDto(Long clientId, String remark){
        this.clientId =clientId;
        this.remark = remark;
    }
     //고객 상세 조회시 조회 되는 dto
    @QueryProjection
    public ClientRemarkDto(Long clientRemarkId, String managerName, String remark, LocalDateTime createdDate){
        this.managerName= managerName;
        this.clientRemarkId =clientRemarkId;
        this.remark = remark;
        this.createdDate =createdDate;
    }


}
