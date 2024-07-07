package com.propertyservice.propertyservice.repository.common;

import com.propertyservice.propertyservice.domain.manager.ManagerState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerStateRepository extends JpaRepository<ManagerState, Long> {
    Optional<ManagerState> findByManagerStateId(Long managerStateId);
}
