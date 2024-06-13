package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.dto.client.ClientDto;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ClientServiceTest {
    @Autowired
    private  ClientService clientService;
    @Test
    public void createClientTest(){
        ClientDto clientDto = new ClientDto();
        clientDto.setClientName("test1");
        clientDto.setClientPhoneNumber("010-1234-1234");
        clientDto.setRemark("특이사항 있음");
        clientDto.setInflowTypeId(1L);
        clientDto.setManagerId(1L);

        clientService.createClientLedger(clientDto);
        
        
    }

}