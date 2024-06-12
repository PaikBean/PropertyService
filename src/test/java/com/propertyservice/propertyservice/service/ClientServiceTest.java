package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.dto.client.ClientLedgerForm;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ClientServiceTest {
    @Autowired
    private  ClientService clientService;
    @Test
    public void createClientTest(){
        ClientLedgerForm clientLedgerForm = new ClientLedgerForm();
        clientLedgerForm.setClientName("test1");
        clientLedgerForm.setClientPhoneNumber("010-1234-1234");
        clientLedgerForm.setRemark("특이사항 있음");
        clientLedgerForm.setInflowTypeId(1L);
        clientLedgerForm.setManagerId(1L);

        clientService.createClientLedger(clientLedgerForm);
        
        
    }

}