package com.propertyservice.propertyservice.domain.property;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@Table(name = "property_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_type_id")
    private Long propertyTypeId;
    @Column(nullable = false)
    private String propertyType;

    @Builder
    public PropertyType(Long propertyTypeId, String propertyType) {
        this.propertyTypeId = propertyTypeId;
        this.propertyType = propertyType;
    }
}
