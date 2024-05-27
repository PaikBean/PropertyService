package com.propertyservice.propertyservice;

import com.propertyservice.propertyservice.config.AppConfig;
import com.propertyservice.propertyservice.utils.validation.ValidBizRegNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PropertyServiceApplication {

    @Autowired
    private AppConfig appConfig;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PropertyServiceApplication.class, args);
        AppConfig appConfig = context.getBean(AppConfig.class);
        ValidBizRegNumber.init(appConfig);
    }

}
