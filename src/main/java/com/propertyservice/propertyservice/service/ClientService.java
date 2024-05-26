package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.dto.client.InflowTypeDto;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateCondition;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateDto;
import com.propertyservice.propertyservice.repository.client.InflowTypeRepository;
import com.propertyservice.propertyservice.repository.common.TransactionTypeRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
import jakarta.persistence.EntityNotFoundException;
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
public class ClientService {

    private final InflowTypeRepository inflowTypeRepository;
    private final PropertyRepository propertyRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    public List<InflowTypeDto> searchInflowTypeList() {
        List<InflowTypeDto> inflowTypeDtoList = new ArrayList<>();

        for (InflowType inflowType : inflowTypeRepository.findAll()) {
            inflowTypeDtoList.add(InflowTypeDto.builder()
                    .inflowType(inflowType)
                    .build());
        }

        return inflowTypeDtoList;
    }

    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        List<ShowingPropertyCandidateDto> showingPropertyCandidateDtoList = propertyRepository.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
        for (ShowingPropertyCandidateDto showingPropertyCandidateDto : showingPropertyCandidateDtoList) {
            Property property = propertyRepository.findById(showingPropertyCandidateDto.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("정보가 잘못되었습니다. 관리자에게 문의하세요."));
            showingPropertyCandidateDto.setPrice(getSummaryPrice(property));
        }
        return showingPropertyCandidateDtoList;
    }

    private String getSummaryPrice(Property property) {
        if (property.getTransactionTypeId() == 1 || property.getTransactionTypeId() == 4)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getDeposit(), property.getMonthlyFee());
        else if (property.getTransactionTypeId() == 2)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getJeonseFee());
        else if (property.getTransactionTypeId() == 3)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getTradeFee());
        else
            return null;
    }
}
