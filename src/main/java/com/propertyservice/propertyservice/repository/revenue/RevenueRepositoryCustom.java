package com.propertyservice.propertyservice.repository.revenue;

import com.propertyservice.propertyservice.dto.revenue.RevenueCondition;
import com.propertyservice.propertyservice.dto.revenue.RevenueDto;

import java.math.BigDecimal;
import java.util.List;

public interface RevenueRepositoryCustom {
    List<RevenueDto> searchRevenueList(RevenueCondition revenueCondition);

    BigDecimal totalCommission(RevenueCondition revenueCondition);

    Long totalCount(RevenueCondition revenueCondition);
}
