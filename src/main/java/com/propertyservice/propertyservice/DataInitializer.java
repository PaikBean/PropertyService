package com.propertyservice.propertyservice;

import com.propertyservice.propertyservice.domain.InflowType;
import com.propertyservice.propertyservice.repository.InflowTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initialData(InflowTypeRepository inflowTypeRepository){
        return args -> {
            if (inflowTypeRepository.count()==0) {
                InflowType type1 = InflowType.builder().inflowType("직방").build();
                InflowType type2 = InflowType.builder().inflowType("다방").build();
                InflowType type3 = InflowType.builder().inflowType("피터팬").build();
                InflowType type4 = InflowType.builder().inflowType("집토스").build();
                InflowType type5 = InflowType.builder().inflowType("기타").build();
                List<InflowType> typeList = Arrays.asList(type1,type2,type3,type4,type5);
                inflowTypeRepository.saveAll(typeList);
            }
        };
    }
}
