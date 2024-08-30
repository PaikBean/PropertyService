package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.revenue.RevenueLedger;
import com.propertyservice.propertyservice.dto.revenue.RevenueForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RevenueServiceTest {
    @Autowired
    private RevenueService revenueService;
    @Test
    public void createTest(){
        RevenueForm revenueForm = RevenueForm.builder()
                .managerId(1L)
                .ownerName("test")
                .clientName("test")
                .addressL1(1L)
                .addressL2(1L)
                .addressL3("asd")

                .transactionType(TransactionType.JEONSE)
                .jeonseFee(BigDecimal.TEN)
                .commission(BigDecimal.TEN)
                .build();

        revenueService.registryRevenue(revenueForm);
    }

}