package com.propertyservice.propertyservice.domain.company;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import com.propertyservice.propertyservice.domain.manager.Manager;
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
    private Company company;
    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentCode;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "manager_id")
    private Manager departmentPresidentName;

    @Builder
    public Department(Long departmentId, Company company, String departmentName, String departmentCode, Manager departmentPresidentName) {
        this.departmentId = departmentId;
        this.company = company;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.departmentPresidentName =departmentPresidentName;
    }
}
