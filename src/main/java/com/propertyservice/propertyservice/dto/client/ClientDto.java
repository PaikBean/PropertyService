package com.propertyservice.propertyservice.dto.client;

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



}
