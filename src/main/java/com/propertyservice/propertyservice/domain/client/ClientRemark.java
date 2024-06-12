package com.propertyservice.propertyservice.domain.client;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientRemark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientRemark {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "client_remark_id")
    private Long clientRemarkId;

    private Long clientId;

    private String remark; // 특이사항

    // 나머지는 client id로 가져오면 될 듯.

    @Builder
    public ClientRemark(Long clientId, String remark){
        this.clientId = clientId;
        this.remark = remark;
    }
}
