package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByBuildingAndUnitNumber(Building building, String unitNumber);

    List<Property> findAllByBuildingBuildingId(Long buildingID);
}
