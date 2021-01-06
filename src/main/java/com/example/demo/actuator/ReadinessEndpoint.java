package com.example.demo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "readiness")
public class ReadinessEndpoint {

    private String readiness = "NOT_READY";

    @ReadOperation
    public Map<String, Object> getReadiness() {
        Map<String, Object> map = new HashMap<>();
        map.put("readiness", readiness);
        return map;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setReadiness() {
        readiness = "READY";
    }
}
