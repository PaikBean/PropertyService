package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.dto.client.ClientLedgerForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    public ClientLedgerForm createClientForm(){
        ClientLedgerForm clientLedgerForm = new ClientLedgerForm();
        clientLedgerForm.setClientName("test1");
        clientLedgerForm.setClientPhoneNumber("010-1234-1234");
        clientLedgerForm.setRemark("특이사항 있음");
        clientLedgerForm.setInflowTypeId(1L);
        clientLedgerForm.setManagerId(1L);

       return  clientLedgerForm;
    }
    @Test
    public void createClientTest(){
        clientController.createClientLedger(createClientForm());
    }

}