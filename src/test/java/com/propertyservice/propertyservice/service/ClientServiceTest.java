package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.dto.client.ClientCondition;
import com.propertyservice.propertyservice.dto.client.ClientDto;
import com.propertyservice.propertyservice.dto.client.ClientForm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ClientServiceTest {
    @Autowired
    private  ClientService clientService;
    @Test
    public void createClientTest(){
        ClientForm clientForm = new ClientForm();
        clientForm.setClientName("test1");
        clientForm.setClientPhoneNumber("010-1234-1234");
        clientForm.setRemark("특이사항 있음");
        clientForm.setInflowTypeId(1L);
        clientForm.setManagerId(1L);

        clientService.createClientLedger(clientForm);
        
        
    }

    @Test
    public void searchClientList(){
        ClientCondition.clientListCondition clientListCondition = new ClientCondition.clientListCondition();
        clientListCondition.setManagerId(1L);
        clientListCondition.setClientPhoneNumber("1234");

        List<ClientDto.ClientListResponseDto> clientListResponseDtoList = clientService.searchClientList(clientListCondition);

        for (ClientDto.ClientListResponseDto clientListResponseDto : clientListResponseDtoList) {
            log.warn(clientListResponseDto.getClientName() + "   "+ clientListResponseDto.getManagerName());
        }

    }

}