package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.building.Building;
import com.propertyservice.propertyservice.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByBuildingAndUnitNumber(Building building, String unitNumber);
}
