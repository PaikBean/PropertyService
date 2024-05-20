package com.propertyservice.propertyservice.domain.address;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "address_level2")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressLevel2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_level2_id")
    private Long addressLevel2Id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "address_level1_id")
    private AddressLevel1 addressLevel1;
    @Column(nullable = false)
    private String addressLevel2;

    @Builder
    public AddressLevel2(Long addressLevel2Id, String addressLevel2) {
        this.addressLevel2Id = addressLevel2Id;
        this.addressLevel2 = addressLevel2;
    }
}
