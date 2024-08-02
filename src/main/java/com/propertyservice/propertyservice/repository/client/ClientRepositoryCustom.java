package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.dto.client.ClientDetailDto;
import com.propertyservice.propertyservice.dto.client.ClientDto;
import com.propertyservice.propertyservice.dto.client.ClientRemarkDto;
import com.propertyservice.propertyservice.dto.client.ShowingPropertySummaryDto;

import java.util.List;

public interface ClientRepositoryCustom {
    List<ClientDto.ClientListResponseDto> searchClientList(String clientName, String clinetPhoneNumber);

    List<ShowingPropertySummaryDto> searchShowingPropertyList(Long clientId, Long prorpertyId);

    List<ClientRemarkDto> searchClientRemark(Long clientId);

    List<ClientDto.ClientListDto> searchClientList(Long companyId);
}
