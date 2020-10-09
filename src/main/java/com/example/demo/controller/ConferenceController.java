package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conferenceData")
@Slf4j
public class ConferenceController {

    @GetMapping("/speakers")
    public void getSpeakers() {
        log.info("Get Speakers");
    }

    @GetMapping("/sessions")
    public void getSessions() {
        log.info("Get Sessions");
    }
}
