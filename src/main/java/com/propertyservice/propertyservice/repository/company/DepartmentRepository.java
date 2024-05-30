package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByDepartmentName(String departmentName);
}
