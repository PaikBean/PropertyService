package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.property.ShowingProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingPropertyRepository extends JpaRepository<ShowingProperty, Long> {
}
