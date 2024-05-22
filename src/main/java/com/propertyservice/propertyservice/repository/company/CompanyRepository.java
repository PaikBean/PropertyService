package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
