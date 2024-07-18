package com.propertyservice.propertyservice.domain.client;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    private Long managerId; // 담당 매니저Id;

    private String clientName;

    private String clientPhoneNumber;

    private InflowType inflowType;

    private Long registrationManagerId;

    private Long modifiedManagerId;



    @Builder
    public Client(Long managerId, String clientName,String clientPhoneNumber, InflowType inflowType, Long registrationManagerId, Long modifiedManagerId){
        this.managerId = managerId;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.inflowType =inflowType;
        this.registrationManagerId =registrationManagerId;
        this.modifiedManagerId = modifiedManagerId;
    }
}
