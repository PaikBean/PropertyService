package com.propertyservice.propertyservice;

import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import com.propertyservice.propertyservice.repository.company.ManagerAddressRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initialInitData(JdbcTemplate jdbcTemplate, AddressLevel1Repository addressLevel1Repository, AddressLevel2Respository addressLevel2Respository) {
        return args -> {
            if(addressLevel1Repository.count() == 0 && addressLevel2Respository.count() == 0){
                try (InputStream inputStream = getClass().getResourceAsStream("/static/sql/insert_init_data.sql");
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Skip empty lines
                        if (StringUtils.hasText(line) && !line.toLowerCase().contains("nan")) {
                            // Execute each insert statement
                            jdbcTemplate.execute(line);
                        }
                    }
                }
            }
        };
    }

    @Bean
    public CommandLineRunner initalDummyData(JdbcTemplate jdbcTemplate, CompanyRepository companyRepository, DepartmentRepository departmentRepository, ManagerRepository managerRepository, ManagerAddressRepository managerAddressRepository) {
        return args -> {
            if(companyRepository.count() == 0 && departmentRepository.count() == 0 && managerRepository.count() == 0 && managerAddressRepository.count() == 0){
                try (InputStream inputStream = getClass().getResourceAsStream("/static/sql/insert_dummy_data.sql");
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Skip empty lines
                        if (StringUtils.hasText(line) && !line.toLowerCase().contains("--")) {
                            // Execute each insert statement
                            jdbcTemplate.execute(line);
                        }
                    }
                }
            }
        };
    }
}
