package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.domain.revenue.RevenueLedger;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.dto.revenue.RevenueCondition;
import com.propertyservice.propertyservice.dto.revenue.RevenueForm;
import com.propertyservice.propertyservice.dto.revenue.RevenueTotalDto;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.revenue.RevenueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RevenueService {
    private final ManagerRepository managerRepository;
    private final RevenueRepository revenueRepository;

    private final EntityExceptionService entityExceptionService;
    private final CommonService commonService;

    @Transactional
    public void registryRevenue(RevenueForm revenueForm) {
        Manager manager = entityExceptionService.findEntityById(
                () -> managerRepository.findById(revenueForm.getManagerId()),
                "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );
        revenueRepository.save(RevenueLedger.builder()
                        .manager(manager)
                        .company(manager.getCompany())
                        .companyCode(manager.getCompany().getCompanyCode())
                        .departmentCode(manager.getDepartment().getDepartmentCode())
                        .ownerName(revenueForm.getOwnerName())
                        .clientName(revenueForm.getClientName())
                        .addressLevel1Id(revenueForm.getAddressL1())
                        .addressLevel2Id(revenueForm.getAddressL2())
                        .addressLevel3(revenueForm.getAddressL3())
                        .contractStartDate(LocalDate.parse(revenueForm.getContractStartDate(),DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay())
                        .contractEndDate(LocalDate.parse(revenueForm.getContractEndDate(),DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay())
                        .transactionType(revenueForm.getTransactionType())
                        .deposit(revenueForm.getDeposit() != null ? revenueForm.getDeposit() : null)
                        .monthlyFee(revenueForm.getMonthlyFee() != null ? revenueForm.getMonthlyFee() : null)
                        .jeonseFee(revenueForm.getJeonseFee() != null ? revenueForm.getJeonseFee() : null)
                        .tradeFee(revenueForm.getTradeFee() != null ? revenueForm.getTradeFee() : null)
                        .commission(revenueForm.getCommission())
                        .remark(revenueForm.getRemark())
                .build());
    }

    public RevenueTotalDto searchRevenueList(RevenueCondition revenueCondition) {
        CustomUserDetail customUserDetail = commonService.getCustomUserDetailBySecurityContextHolder();
        //UserDetails userDetails = .getCustomUserDetail();
        log.info("CustomuserDetail : " + customUserDetail.getManagerName());

        Long companyId = commonService.getCustomUserDetailBySecurityContextHolder().getCompany().getCompanyId();
        if(companyId == null)
            throw new IllegalArgumentException("등록 회사를 찾을 수 없습니다.");

        // log.info(revenueCondition.toString());
        revenueCondition.setCompanyId(companyId);

        return RevenueTotalDto.builder()
                .totalCount(revenueRepository.totalCount(revenueCondition))
                .totalCommission(revenueRepository.totalCommission(revenueCondition))
                .revenueDtoList(revenueRepository.searchRevenueList(revenueCondition))
                .build();
    }

    @Transactional
    public void deleteRevenue(Long revenueId) {
        revenueRepository.delete(entityExceptionService.findEntityById(
                () -> revenueRepository.findById(revenueId),
                "장부 정보가 존재하지 않습니다. 관리자에게 문의하세요.")
        );
    }
}
