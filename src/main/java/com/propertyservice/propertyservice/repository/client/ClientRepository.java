package com.propertyservice.propertyservice.repository.client;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

//    @Query("select c " +
//            "from Client as c " +
//            "where  c.clientPhoneNumber like %:clientPhoneNumber% and c.managerId = :managerId " +
//            "order by c.clientId " )
//    List<Client> findByClientPhoneNumberAndManagerId(Long managerId, String clientPhoneNumber);

    List<Client> findAllByCompany(Company company);
}
