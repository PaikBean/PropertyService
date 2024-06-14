package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.dto.client.ClientCondition;
import com.propertyservice.propertyservice.dto.client.ClientDto;

import java.util.List;

public interface ClientRepositoryCustom {
    List<ClientDto.ClientListResponseDto> searchClientList(Long managerId, String clinetPhoneNumber);

    List<ClientDto.ClientDetailResponseDto> searchClientDetail(Long clientId);
}
