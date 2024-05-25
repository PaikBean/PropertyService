package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
