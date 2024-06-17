package com.propertyservice.propertyservice.repository.revenue;

import com.propertyservice.propertyservice.domain.revenue.RevenueLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<RevenueLedger, Long> {
}
