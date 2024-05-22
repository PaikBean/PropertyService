package com.propertyservice.propertyservice;

import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.repository.client.InflowTypeRepository;
import com.propertyservice.propertyservice.repository.common.TransactionTypeRepository;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initialData(InflowTypeRepository inflowTypeRepository) {
        return args -> {
            if (inflowTypeRepository.count() == 0) {
                InflowType type1 = InflowType.builder().inflowType("직방").build();
                InflowType type2 = InflowType.builder().inflowType("다방").build();
                InflowType type3 = InflowType.builder().inflowType("피터팬").build();
                InflowType type4 = InflowType.builder().inflowType("집토스").build();
                InflowType type5 = InflowType.builder().inflowType("기타").build();
                List<InflowType> typeList = Arrays.asList(type1, type2, type3, type4, type5);
                inflowTypeRepository.saveAll(typeList);
            }
        };
    }

    @Bean
    public CommandLineRunner initialAddressData(JdbcTemplate jdbcTemplate,
                                                AddressLevel1Repository addressLevel1Repository,
                                                AddressLevel2Respository addressLevel2Respository) {
        return args -> {
            if (addressLevel1Repository.count() == 0 && addressLevel2Respository.count() == 0) {
                try (InputStream inputStream = getClass().getResourceAsStream("/static/sql/insert_address_data.sql");
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Skip empty lines
                        if (StringUtils.hasText(line) && !line.toLowerCase().contains("nan")) {
                            // Execute each insert statement
                            jdbcTemplate.execute(line);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Bean
    public CommandLineRunner initTransactionTypeData(TransactionTypeRepository transactionTypeRepository) {
        return args -> {
            if (transactionTypeRepository.count() == 0) {
                TransactionType type1 = TransactionType.builder()
                        .transactionTypeName("월세")
                        .transactionTypeCode("monthly")
                        .build();
                TransactionType type2 = TransactionType.builder()
                        .transactionTypeName("전세")
                        .transactionTypeCode("jeonse")
                        .build();
                TransactionType type3 = TransactionType.builder()
                        .transactionTypeName("매매")
                        .transactionTypeCode("trade")
                        .build();
                TransactionType type4 = TransactionType.builder()
                        .transactionTypeName("단기")
                        .transactionTypeCode("shorterm")
                        .build();
                List<TransactionType> typeList = Arrays.asList(type1, type2, type3, type4);
                transactionTypeRepository.saveAll(typeList);
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
