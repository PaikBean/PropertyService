package com.propertyservice.propertyservice.domain.address;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "address_level1")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressLevel1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_level1_id")
    private Long addressLevel1Id;
    @Column(nullable = false)
    private String addressLevel1;

    @Builder
    public AddressLevel1(Long addressLevel1Id, String addressLevel1) {
        this.addressLevel1Id = addressLevel1Id;
        this.addressLevel1 = addressLevel1;
    }
}
