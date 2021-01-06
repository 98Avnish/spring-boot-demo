package com.example.demo.actuator;

import com.example.demo.properties.CustomProperties;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfo implements InfoContributor {

    private final CustomProperties customProperties;

    public CustomInfo(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder
                .withDetail("Flag", customProperties.getFlag())
                .withDetail("Extra Info", "ABC")
                .withDetail("Extra Extra Info", "XYZ");
    }
}
