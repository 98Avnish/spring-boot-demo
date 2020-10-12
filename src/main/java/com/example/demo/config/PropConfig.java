package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "custom")
public class PropConfig {

    private String propertyOne;
    private String propertyTwo;
    private Boolean flag;
}
