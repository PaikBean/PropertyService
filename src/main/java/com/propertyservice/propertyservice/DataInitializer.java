package com.propertyservice.propertyservice;

import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.common.ManagerState;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.repository.client.InflowTypeRepository;
import com.propertyservice.propertyservice.repository.common.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initialInitData(JdbcTemplate jdbcTemplate) {
        return args -> {
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

        };
    }

    @Bean
    public CommandLineRunner initalDummyData(JdbcTemplate jdbcTemplate) {
        return args -> {
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}
