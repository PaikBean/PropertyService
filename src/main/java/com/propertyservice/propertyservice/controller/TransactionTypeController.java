package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.response.Response;
import com.propertyservice.propertyservice.domain.response.ResponseCode;
import com.propertyservice.propertyservice.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    /**
     * 거래유형 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/transaction-type-list")
    public Response searchInflowTypeList() {
        try {
            return new Response(ResponseCode.SUCCESS, transactionTypeService.searchInflowTypeList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
