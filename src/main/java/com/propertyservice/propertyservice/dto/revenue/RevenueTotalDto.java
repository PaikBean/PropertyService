package com.propertyservice.propertyservice.dto.revenue;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class RevenueTotalDto {
    private Long totalCount;
    private BigDecimal totalCommission;
    private List<RevenueDto> revenueDtoList;

    @Builder
    public RevenueTotalDto(Long totalCount, BigDecimal totalCommission, List<RevenueDto> revenueDtoList) {
        this.totalCount = totalCount;
        this.totalCommission = totalCommission;
        this.revenueDtoList = revenueDtoList;
    }
}
