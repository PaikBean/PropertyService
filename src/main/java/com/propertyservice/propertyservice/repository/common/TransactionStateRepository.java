package com.propertyservice.propertyservice.repository.common;

import com.propertyservice.propertyservice.domain.common.TransactionState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStateRepository extends JpaRepository<TransactionState, Long> {
}
