package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>, ManagerRepositoryCustom {
    Optional<Manager> findByManagerId(Long managerId);

    Optional<Manager> findByManagerEmail(String managerEmail);

    List<Manager> findAllByCompany(Company company);
}
