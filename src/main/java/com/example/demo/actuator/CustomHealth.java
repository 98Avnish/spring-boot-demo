package com.example.demo.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealth implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        return health();
    }

    @Override
    public Health health() {
        return Health.up().withDetail("Status", "All checks clear").build();
    }
}
