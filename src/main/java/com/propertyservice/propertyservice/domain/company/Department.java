package com.propertyservice.propertyservice.domain.company;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "department")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company companyId;
    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentCode;

    @Builder
    public Department(Long departmentId, Company companyId, String departmentName, String departmentCode) {
        this.departmentId = departmentId;
        this.companyId = companyId;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
}
