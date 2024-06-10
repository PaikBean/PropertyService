package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);
}
