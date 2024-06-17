package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.revenue.RevenueCondition;
import com.propertyservice.propertyservice.dto.revenue.RevenueForm;
import com.propertyservice.propertyservice.repository.revenue.RevenueRepository;
import com.propertyservice.propertyservice.service.RevenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/revenue")
public class RevenueController {

    private final RevenueService revenueService;
    private final RevenueRepository revenueRepository;

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

    /**
     * 매출 장무 목록 조회
     *
     * @param revenueCondition
     * @return
     */
    @GetMapping("/v1/revenue-ledger-list")
    public Response searchRevenueList(RevenueCondition revenueCondition) {
        try {
            return new Response(ResponseCode.SUCCESS, revenueService.searchRevenueList(revenueCondition), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 매출 장부 삭제
     *
     * @param revenueId
     * @return
     */
    @DeleteMapping("/v1/revenue-ledger/{revenueId}")
    public Response deleteRevenue(@PathVariable(name = "revenueId") Long revenueId) {
        try {
            revenueService.deleteRevenue(revenueId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
