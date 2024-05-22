package com.propertyservice.propertyservice.domain.company;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;
    @Column(nullable = false)
    private String companyCode;
    @Column(nullable = false)
    private Long companyAddressId;
    private Long president_id;
//    @Column(nullable = false) Todo : 사업자등록번호 검증 로직 추가 예정
    private String businessRegistrationNumber;
    private LocalDateTime serviceStartDate;
    private LocalDateTime serviceEndDate;

    @Builder
    public Company(Long companyId, String companyCode, Long companyAddressId, Long president_id, String businessRegistrationNumber, LocalDateTime serviceStartDate, LocalDateTime serviceEndDate) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyAddressId = companyAddressId;
        this.president_id = president_id;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
    }
}
