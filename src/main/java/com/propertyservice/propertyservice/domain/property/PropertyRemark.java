package com.propertyservice.propertyservice.domain.property;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "property_remark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PropertyRemark extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remark_id")
    private Long remarkId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
    private String remark;

    @Builder
    public PropertyRemark(Long remarkId, Property property, String remark) {
        this.remarkId = remarkId;
        this.property = property;
        this.remark = remark;
    }
}
