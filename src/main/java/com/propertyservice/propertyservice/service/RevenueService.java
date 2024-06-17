package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Manager;
import com.propertyservice.propertyservice.domain.revenue.RevenueLedger;
import com.propertyservice.propertyservice.dto.revenue.RevenueForm;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.revenue.RevenueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RevenueService {
    private final ManagerRepository managerRepository;
    private final RevenueRepository revenueRepository;
    @Transactional
    public void registryRevenue(RevenueForm revenueForm) {
        Manager manager = managerRepository.findById(revenueForm.getManagerId()).orElseThrow(
                () -> new EntityNotFoundException("등록되지 않은 매니저 입니다."));
        revenueRepository.save(RevenueLedger.builder()
                        .managerId(manager)
                        .companyId(manager.getCompany_id())
                        .companyCode(manager.getCompany_id().getCompanyCode())
                        .departmentCode(manager.getDepartment_id().getDepartmentCode())
                        .ownerName(revenueForm.getOwnerName())
                        .clientName(revenueForm.getClientName())
                        .addressLevel1Id(revenueForm.getAddressL1())
                        .addressLevel2Id(revenueForm.getAddressL2())
                        .addressLevel3(revenueForm.getAddressL3())
                        .contractStartDate(LocalDate.parse(revenueForm.getContractStartDate(),DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay())
                        .contractEndDate(LocalDate.parse(revenueForm.getContractEndDate(),DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay())
                        .transactionTypeId(revenueForm.getTransactionTypeId())
                        .deposit(revenueForm.getDeposit() != null ? revenueForm.getDeposit() : null)
                        .monthlyFee(revenueForm.getMonthlyFee() != null ? revenueForm.getMonthlyFee() : null)
                        .jeonseFee(revenueForm.getJeonseFee() != null ? revenueForm.getJeonseFee() : null)
                        .tradeFee(revenueForm.getTradeFee() != null ? revenueForm.getTradeFee() : null)
                        .commission(revenueForm.getCommission())
                        .remark(revenueForm.getRemark())
                .build());
    }
}
