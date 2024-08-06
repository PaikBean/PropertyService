package com.propertyservice.propertyservice.dto.client;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ClientInfoDto {
    private Long clientId;
    private String clientName;
    private String inflowType;
    private String clientPhoneNumber;
    private Long managerId;

    List<ClientRemarkDto> clientRemarkList;


    @Builder
    public ClientInfoDto(Long clientId, String clientName, String inflowType, String clientPhoneNumber, Long managerId, List<ClientRemarkDto> clientRemarkList){
        this.clientId = clientId;
        this.clientName = clientName;
        this.inflowType =inflowType;
        this.clientPhoneNumber = clientPhoneNumber;
        this.managerId = managerId;
        this.clientRemarkList =clientRemarkList;
    }
}
