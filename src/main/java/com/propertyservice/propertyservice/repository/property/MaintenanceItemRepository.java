package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.property.MaintenanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceItemRepository extends JpaRepository<MaintenanceItem, Long> {
}
