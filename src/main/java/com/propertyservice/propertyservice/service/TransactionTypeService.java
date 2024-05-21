package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.TransactionType;
import com.propertyservice.propertyservice.dto.transactiontype.TransactionTypeDto;
import com.propertyservice.propertyservice.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;
    public List<TransactionTypeDto> searchInflowTypeList() {
        List<TransactionTypeDto> transactionTypeDtoList = new ArrayList<>();
        for (TransactionType type : transactionTypeRepository.findAllByIsUsed(true)) {
            transactionTypeDtoList.add(TransactionTypeDto.builder()
                            .transactionTypeId(type.getTransactionTypeId())
                            .transactionTypeCode(type.getTransactionTypeCode())
                            .transactionTypeName(type.getTransactionTypeName())
                    .build());
        }
        return transactionTypeDtoList;
    }
}
