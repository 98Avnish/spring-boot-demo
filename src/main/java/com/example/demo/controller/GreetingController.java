package com.example.demo.controller;

import com.example.demo.config.PropConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@Slf4j
public class GreetingController {

//    @Value("${custom.property-name}")
    @Inject
    private PropConfig props;

    @GetMapping
    public String greeting() {
        return new StringBuilder("<h1>Hello!!</h1>")
                .append("<a href=/login>Login</a>")
                .append("<br><br>")
                .append("<a href=/perform_logout>Logout</a>")
                .toString();
    }

    @GetMapping("/value")
    public String value() {
        return "Value :" + (Boolean.TRUE.equals(props.getFlag())
                ? props.getPropertyName() : "Dummy");
    }
}
