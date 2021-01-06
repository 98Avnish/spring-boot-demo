package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConditionalConfig {

    @Bean
    @ConditionalOnBean(name = "objectMapper")
    public void demoBean() {
        log.info("objectMapper bean found");
    }

    @Bean
    @ConditionalOnMissingBean(name = "objectMapper1")
    public void demoMissingBean() {
        log.info("objectMapper1 bean not found");
    }

    @Bean
    @ConditionalOnProperty(
//            name = "propertyName",
            value = "property-name",
            prefix = "custom",
            matchIfMissing = true,
            havingValue = "Foo"
    )
    public void demoProp() {
        // Value - Foo will match
        // Value - false/Foo0 will not match
        // Match if Missing - To disable this bean explicitly set False
        log.info("Custom property found");
    }
}
