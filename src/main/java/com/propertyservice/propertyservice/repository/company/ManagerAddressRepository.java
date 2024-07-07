package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.manager.ManagerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAddressRepository extends JpaRepository<ManagerAddress, Long> {
}
