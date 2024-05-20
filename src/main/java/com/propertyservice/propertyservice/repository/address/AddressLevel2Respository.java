package com.propertyservice.propertyservice.repository.address;

import com.propertyservice.propertyservice.domain.address.AddressLevel2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressLevel2Respository extends JpaRepository<AddressLevel2, Long> {
    List<AddressLevel2> findByAddressLevel1_AddressLevel1Id(Long addressLevel1Id);
}
