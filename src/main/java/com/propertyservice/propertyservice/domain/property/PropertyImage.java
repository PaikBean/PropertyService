package com.propertyservice.propertyservice.domain.property;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "property_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_image_id")
    private Long propertyImageId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
    private String imageName;
    private Long fileSize;
    @Column(nullable = false)
    private String filePath;
    @Column(nullable = false)
    private String fileUrl;

    @Builder
    public PropertyImage(Long propertyImageId, Property property, String imageName, Long fileSize, String filePath, String fileUrl) {
        this.propertyImageId = propertyImageId;
        this.property = property;
        this.imageName = imageName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
    }
}
