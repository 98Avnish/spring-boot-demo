package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private String propertyName;
    private Boolean flag;
}
