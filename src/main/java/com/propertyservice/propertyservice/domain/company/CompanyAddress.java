package com.propertyservice.propertyservice.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "company_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_address_id")
    private Long companyAddressId;
    @Column(nullable = false)
    private Long addressLevel1Id;
    @Column(nullable = false)
    private Long AddressLevel2Id;
    @Column(nullable = false)
    private String addressLevel3;

    @Builder
    public CompanyAddress(Long companyAddressId, Long addressLevel1Id, Long addressLevel2Id, String addressLevel3) {
        this.companyAddressId = companyAddressId;
        this.addressLevel1Id = addressLevel1Id;
        AddressLevel2Id = addressLevel2Id;
        this.addressLevel3 = addressLevel3;
    }
}
