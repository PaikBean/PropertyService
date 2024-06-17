package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.revenue.RevenueForm;
import com.propertyservice.propertyservice.service.RevenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/revenue")
public class RevenueController {

    private final RevenueService revenueService;
    /**
     * 매출 장부 등록
     *
     * @param revenueForm
     * @return
     */
    @PostMapping("/v1/revenue-ledger")
    public Response registryRevenue(@RequestBody @Valid RevenueForm revenueForm) {
        try {
            revenueService.registryRevenue(revenueForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
