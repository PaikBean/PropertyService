package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
