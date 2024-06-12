package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
