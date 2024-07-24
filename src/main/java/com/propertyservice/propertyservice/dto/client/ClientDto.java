package com.propertyservice.propertyservice.dto.client;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

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

    @Getter
    public static class ClientListDto{
        private String clientName;
        private Long clientId;

        @QueryProjection
        public ClientListDto(Long clientId, String clientName){
            this.clientId = clientId;
            this.clientName = clientName;
        }
    }

}
