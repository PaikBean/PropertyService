package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.domain.building.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOwnerPhoneNumber(String ownerPhoneNumber);
}