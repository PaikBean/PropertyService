package com.propertyservice.propertyservice.domain.company;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "manager_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_address_id")
    private Long managerAddressId;
    @Column(nullable = false)
    private Long addressLevel1Id;
    @Column(nullable = false)
    private Long addressLevel2Id;
    @Column(nullable = false)
    private String addressLevel3;

    @Builder
    public ManagerAddress( Long addressLevel1Id, Long addressLevel2Id, String addressLevel3){
        this.addressLevel1Id= addressLevel1Id;
        this.addressLevel2Id =addressLevel2Id;
        this.addressLevel3 = addressLevel3;
    }
}
