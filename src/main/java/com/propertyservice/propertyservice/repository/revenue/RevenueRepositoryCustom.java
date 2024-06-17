package com.propertyservice.propertyservice.repository.revenue;

import com.propertyservice.propertyservice.dto.revenue.RevenueCondition;
import com.propertyservice.propertyservice.dto.revenue.RevenueDto;

import java.util.List;

public interface RevenueRepositoryCustom {
    List<RevenueDto> searchRevenueList(RevenueCondition revenueCondition);
}
