package com.propertyservice.propertyservice.repository.common;

import com.propertyservice.propertyservice.domain.common.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
