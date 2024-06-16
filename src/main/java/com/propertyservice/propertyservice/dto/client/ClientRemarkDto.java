package com.propertyservice.propertyservice.dto.client;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClientRemarkDto {
    private Long managerId;
    private String managerName;
    private Long clientId;
    private String remark;
    private LocalDateTime createdDate; //등록일.

    public ClientRemarkDto(Long clientId, String remark){
        this.clientId =clientId;
        this.remark = remark;
    }
    // 고객 상세 조회시 조회 되는 dto
    @QueryProjection
    public ClientRemarkDto(Long managerId, String managerName, Long clientId, String remark, LocalDateTime createdDate){
        this.managerId = managerId;
        this.managerName= managerName;
        this.clientId =clientId;
        this.remark = remark;
        this.createdDate =createdDate;
    }
}
