package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.property.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {
}
