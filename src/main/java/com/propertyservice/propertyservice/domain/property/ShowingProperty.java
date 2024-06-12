package com.propertyservice.propertyservice.domain.property;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "showing_property")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowingProperty extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showing_property_id")
    private Long  showingPropertyId;

    private Long propertyId; //매물 id

    private Long clientId; // 고객 id

    private Long registrationManagerId;

    private Long modifiedManagerId;

    @Builder
    public ShowingProperty(Long propertyId, Long clientId, Long registrationManagerId, Long modifiedManagerId){
        this.propertyId = propertyId;
        this.clientId = clientId;
        this.registrationManagerId = registrationManagerId;
        this.modifiedManagerId =modifiedManagerId;
    }

}
