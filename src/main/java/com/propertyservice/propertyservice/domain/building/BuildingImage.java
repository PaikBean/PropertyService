package com.propertyservice.propertyservice.domain.building;

import com.propertyservice.propertyservice.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "building_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_image_id")
    private Long buildingImageId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "building_id")
    private Building building;
    @Column(nullable = false)
    private String imageName;
    private Long fileSize;
    private String filePath;
    @Column(nullable = false)
    private String fileUrl;

    @Builder
    public BuildingImage(Long buildingImageId, Building building, String imageName, Long fileSize, String filePath, String fileUrl) {
        this.buildingImageId = buildingImageId;
        this.building = building;
        this.imageName = imageName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
    }
}
