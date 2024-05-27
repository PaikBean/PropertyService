package com.propertyservice.propertyservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${api.service-key}")
    private String serviceKey;

    @Value("${api.url}")
    private String apiUrl;
}
