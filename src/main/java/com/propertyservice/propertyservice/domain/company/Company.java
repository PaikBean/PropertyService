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
    private String companyName;
    @Column(nullable = false)
    private Long companyAddressId;
    @Column(nullable = false)
    private String presidentName;
    @Column(nullable = false)
    private String companyEmail;        // 대표 메일. Todo : 회사코드 신규, 재발급시 사용 예정
    @Column(nullable = false)
    private String businessRegistrationNumber;
    private LocalDateTime serviceStartDate;
    private LocalDateTime serviceEndDate;

    @Builder
    public Company(Long companyId, String companyCode, String companyName, Long companyAddressId, String presidentName, String companyEmail, String businessRegistrationNumber, LocalDateTime serviceStartDate, LocalDateTime serviceEndDate) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyAddressId = companyAddressId;
        this.presidentName = presidentName;
        this.companyEmail = companyEmail;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
    }
}
