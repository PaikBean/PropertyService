package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.client.ClientRemark;
import com.propertyservice.propertyservice.domain.property.ShowingProperty;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ClientDto {

    @Getter
    public static class ClientListResponseDto{
        private Long managerId;
        private String managerName;
        private String clientName;
        private Long clientId;

//        @QueryProjection
        public ClientListResponseDto(Long managerId, String managerName, String clientName, Long clientId){
            this.managerId = managerId;
            this.managerName =managerName;
            this.clientName =clientName;
            this.clientId = clientId;
        }
    }



}
