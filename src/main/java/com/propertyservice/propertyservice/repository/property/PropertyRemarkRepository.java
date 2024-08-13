package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.domain.property.PropertyRemark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRemarkRepository extends JpaRepository<PropertyRemark, Long> {
//    List<PropertyRemark> findByPropertyPropertyId(Long propertyId);
//    void deleteAllByProperty(Property property);
}
