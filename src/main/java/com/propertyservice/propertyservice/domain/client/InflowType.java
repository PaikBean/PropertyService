package com.propertyservice.propertyservice.domain.client;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "inflow_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InflowType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inflow_type_id")
    private Long id;
    @Column(nullable = false)
    private String inflowType;

    @Builder
    public InflowType(Long id, String inflowType) {
        this.id = id;
        this.inflowType = inflowType;
    }
}
