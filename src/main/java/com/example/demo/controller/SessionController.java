package com.example.demo.controller;

import com.example.demo.model.Session;
import com.example.demo.service.SessionService;
import com.example.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("conferenceData/sessions")
public class SessionController {

    private final SessionService sessionService;

    private final Utils utils;

    public SessionController(SessionService sessionService, Utils utils) {
        this.sessionService = sessionService;
        this.utils = utils;
    }

    @GetMapping
    public List<Session> getSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSession(@PathVariable("id") Long id) {
        return sessionService.getSession(id).map(session ->
                ResponseEntity
                        .ok()
                        .body(session)
        ).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Session> addSession(@Valid @RequestBody Session session,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            utils.logBindingError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(session);
        }
        Session savedSession = sessionService.addSession(session);
        return sessionService.getSession(savedSession.getId()).map(s ->
                ResponseEntity
                        .ok()
                        .body(s)
        ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Session session,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            utils.logBindingError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(session);
        }
        return sessionService.getSession(id).map(s -> {
            Session updatedSession = sessionService.updateSession(session);
            return ResponseEntity
                    .ok()
                    .body(updatedSession);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSession(@PathVariable("id") Long id) {
        return sessionService.getSession(id).map(s -> {
            sessionService.deleteSession(id);
            return ResponseEntity.ok(true);
        }).orElse(ResponseEntity.notFound().build());
    }
}
