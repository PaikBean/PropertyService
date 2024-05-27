package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.ManagerState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerStateRepository extends JpaRepository<ManagerState, Long> {
}
