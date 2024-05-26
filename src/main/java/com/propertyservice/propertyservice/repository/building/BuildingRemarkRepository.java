package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.BuildingRemark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRemarkRepository extends JpaRepository<BuildingRemark, Long> {
    List<BuildingRemark> findAllByBuildingBuildingId(Long buildingId);
}
