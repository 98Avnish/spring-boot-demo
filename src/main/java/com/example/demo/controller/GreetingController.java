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

    @GetMapping("/greeting")
    public String greeting() {
        return "Value :" +
                (Boolean.TRUE.equals(props.getFlag()) ? props.getPropertyOne() : props.getPropertyTwo());
    }
}
