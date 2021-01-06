package com.example.demo.controller;

import com.example.demo.model.Speaker;
import com.example.demo.service.SpeakerService;
import com.example.demo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("conferenceData/speakers")
@Slf4j
public class SpeakerController {

    private final SpeakerService speakerService;
    private final Utils utils;

    public SpeakerController(SpeakerService speakerService, Utils utils) {
        this.speakerService = speakerService;
        this.utils = utils;
    }

    @GetMapping
    public ResponseEntity<List<Speaker>> getSpeakers() {
        ResponseEntity<List<Speaker>> response;
        try {
            List<Speaker> speakers = speakerService.getAllSpeakers();
            response = ResponseEntity.ok().body(speakers);
        } catch (Exception e) {
            log.error("EXCEPTION - Getting All Speakers");
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speaker> getSpeaker(@PathVariable("id") Long id) {
        ResponseEntity<Speaker> response;
        try {
            Speaker speaker = speakerService.getSpeaker(id);
            response = ResponseEntity.ok().body(speaker);
        } catch (Exception e ) {
            log.error("EXCEPTION - Speaker Not Found :{}", id);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Speaker> addSpeakers(@Valid @RequestBody Speaker speaker,
                                               BindingResult bindingResult) {
        ResponseEntity<Speaker> response;
        if (!bindingResult.hasErrors()) {
            try {
                Speaker sp = speakerService.addSpeaker(speaker);
                response = ResponseEntity.ok().body(sp);
            } catch (Exception e) {
                log.error("EXCEPTION - Saving Speaker :{}", speaker);
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            utils.logBindingError(bindingResult);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Speaker> updateSpeaker(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Speaker speaker,
                                                 BindingResult bindingResult) {
        ResponseEntity<Speaker> response;
        if (!bindingResult.hasErrors()) {
            try {
                Speaker sp = speakerService.updateSpeaker(speaker);
                response = ResponseEntity.ok().body(sp);
            } catch (Exception e) {
                log.error("EXCEPTION - Updating Speaker :{}", speaker);
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            utils.logBindingError(bindingResult);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSpeaker(@PathVariable("id") Long id) {
        ResponseEntity<Boolean> response;
        try {
            speakerService.deleteSpeaker(id);
            response = ResponseEntity.ok().body(true);
        } catch (Exception e) {
            log.error("EXCEPTION - Deleting Speaker :{}", id);
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }
}
