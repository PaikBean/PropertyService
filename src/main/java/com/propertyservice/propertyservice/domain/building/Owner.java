package com.propertyservice.propertyservice.domain.building;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(nullable = false)
    private String ownerName;
    private String ownerRelation;
    @Column(nullable = false)
    private String ownerPhoneNumber;

    @Builder
    public Owner(Long ownerId, String ownerName, String ownerRelation, String ownerPhoneNumber) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public void updateOwner(String ownerName, String ownerRelation, String ownerPhoneNumber) {
        this.ownerName = ownerName;
        this.ownerRelation = ownerRelation;
        this.ownerPhoneNumber = ownerPhoneNumber;
    }
}
