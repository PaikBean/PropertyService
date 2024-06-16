package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.domain.company.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Long> {
}
