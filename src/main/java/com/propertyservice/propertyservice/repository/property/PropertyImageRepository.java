package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.property.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
}
