package com.propertyservice.propertyservice.repository;

import com.propertyservice.propertyservice.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    List<TransactionType> findAllByIsUsed(Boolean isUsed);
}
