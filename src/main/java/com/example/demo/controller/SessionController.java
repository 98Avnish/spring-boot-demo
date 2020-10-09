package com.example.demo.controller;

import com.example.demo.model.Session;
import com.example.demo.service.SessionService;
import com.example.demo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("conferenceData/sessions")
@Slf4j
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Utils utils;

    @GetMapping
    public List<Session> getSessions() {
        log.info("Get Sessions");
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSpeaker(@PathVariable("id") Long id) {
        log.info("Get Session {}", id);
        return sessionService.getSession(id).map(session ->
                ResponseEntity
                        .ok()
                        .body(session)
        ).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Session> addSpeaker(@Valid @RequestBody Session session,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            utils.logBindingError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Adding Session: {}", session);
        sessionService.addSession(session);
        return sessionService.getSession(session.getId()).map(s ->
                ResponseEntity
                        .ok()
                        .body(s)
        ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSpeaker(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Session session,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            utils.logBindingError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Updating Session: {}", session);
        return sessionService.getSession(id).map(s -> {
            Session updatedSession = sessionService.updateSession(session);
            return ResponseEntity
                    .ok()
                    .body(updatedSession);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSession(@PathVariable("id") Long id) {
        log.info("Delete Session {}", id);
        return sessionService.getSession(id).map(s -> {
            sessionService.deleteSession(id);
            return ResponseEntity.ok(true);
        }).orElse(ResponseEntity.notFound().build());
    }
}
