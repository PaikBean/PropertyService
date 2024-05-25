package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long>, BuildingRepositoryCustom {
}
