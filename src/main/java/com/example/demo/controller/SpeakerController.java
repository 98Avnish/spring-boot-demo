package com.example.demo.controller;

import com.example.demo.model.Speaker;
import com.example.demo.service.SpeakerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conferenceData/speakers")
@Slf4j
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @GetMapping
    public List<Speaker> getSpeakers() {
        log.info("Get Speakers");
        return speakerService.getAllSpeakers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speaker> getSpeaker(@PathVariable("id") Long id) {
        log.info("Get Speaker {}", id);
        return speakerService.getSpeaker(id).map(speaker ->
                ResponseEntity
                        .ok()
                        .body(speaker))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Speaker> addSpeakers(@RequestBody Speaker speaker) {
        log.info("Adding Speaker: {}", speaker);
        speakerService.addSpeaker(speaker);
        return speakerService.getSpeaker(speaker.getId()).map(sp ->
                ResponseEntity
                        .ok()
                        .body(sp))
                .orElse(ResponseEntity.notFound().build());
    }
}
