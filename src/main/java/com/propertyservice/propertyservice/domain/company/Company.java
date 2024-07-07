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
    private Long companyId;             // 사업체 Id
    @Column(nullable = false)
    private String companyCode;         // 사업체 코드
    private String companyName;         // 사업체 이름
    @Column(nullable = false)
    private String bizNumber;           // 사업체 사업자 등록번호
    private String presidentName;       // 대표자명
    @Column(nullable = false)
    private String companyEmail;        // 사업체 이메일
    private String companyNumber;       // 사업체 전화번호
    private LocalDateTime serviceStartDate;         // 서비스 시작일
    private LocalDateTime serviceEndDate;           // 서비스 시작일

    @Builder
    public Company(Long companyId, String companyCode, String companyName, String bizNumber, String presidentName, String companyEmail, String companyNumber, LocalDateTime serviceStartDate, LocalDateTime serviceEndDate) {
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.bizNumber = bizNumber;
        this.presidentName = presidentName;
        this.companyEmail = companyEmail;
        this.companyNumber = companyNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
    }
}
