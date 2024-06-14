package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.dto.client.ClientForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    public ClientForm createClientForm(){
        ClientForm clientForm = new ClientForm();
        clientForm.setClientName("test1");
        clientForm.setClientPhoneNumber("010-1234-1234");
        clientForm.setRemark("특이사항 있음");
        clientForm.setInflowTypeId(1L);
        clientForm.setManagerId(1L);

       return clientForm;
    }
    @Test
    public void createClientTest(){
        clientController.createClient(createClientForm());
    }

}