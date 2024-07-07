package com.propertyservice.propertyservice.domain.revenue;


import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.manager.Manager;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "revenue_ledger")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RevenueLedger extends BaseTimeEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_ledger_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company companyId;
    private String companyCode;
    private String departmentCode;
    private String ownerName;
    private String clientName;
    @Column(name = "address_level1_id")
    private Long addressLevel1Id;
    @Column(name = "address_level2_id")
    private Long addressLevel2Id;
    private String addressLevel3;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    @Column(nullable = false)
    private Long transactionTypeId;
    private BigDecimal deposit;
    private BigDecimal monthlyFee;
    private BigDecimal jeonseFee;
    private BigDecimal tradeFee;
    @Column(nullable = false)
    private BigDecimal commission;
    private String remark;

    @Builder
    public RevenueLedger(Long id, Manager managerId, Company companyId, String companyCode, String departmentCode, String ownerName, String clientName, Long addressLevel1Id, Long addressLevel2Id, String addressLevel3, LocalDateTime contractStartDate, LocalDateTime contractEndDate, Long transactionTypeId, BigDecimal deposit, BigDecimal monthlyFee, BigDecimal jeonseFee, BigDecimal tradeFee, BigDecimal commission, String remark) {
        this.id = id;
        this.managerId = managerId;
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.departmentCode = departmentCode;
        this.ownerName = ownerName;
        this.clientName = clientName;
        this.addressLevel1Id = addressLevel1Id;
        this.addressLevel2Id = addressLevel2Id;
        this.addressLevel3 = addressLevel3;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.transactionTypeId = transactionTypeId;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.jeonseFee = jeonseFee;
        this.tradeFee = tradeFee;
        this.commission = commission;
        this.remark = remark;
    }
}
