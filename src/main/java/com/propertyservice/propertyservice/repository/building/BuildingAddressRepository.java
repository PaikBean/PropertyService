package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.BuildingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingAddressRepository extends JpaRepository<BuildingAddress, Long> {
}
