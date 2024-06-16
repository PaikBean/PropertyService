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
        for(int i =0 ; i < 3; i++) {
            ClientForm clientForm = new ClientForm();
            clientForm.setClientName("test1");
            clientForm.setClientPhoneNumber("010-1234-1234"+i);
            clientForm.setRemark("특이사항 있음");
            clientForm.setInflowTypeId(1L);
            clientForm.setManagerId(1L);

            clientService.createClient(clientForm);
        }
        
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

    @Test
    public void searchClientDetail(){
        ClientCondition.clientDetailCondition clientDetailCondition = new ClientCondition.clientDetailCondition();
        clientDetailCondition.setClientId(1L);
        clientDetailCondition.setPropertyId(1L);

        log.warn(clientService.searchClientDetailList(clientDetailCondition).getClientRemarkList().toString());
    }

}