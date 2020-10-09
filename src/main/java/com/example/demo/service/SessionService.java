package com.example.demo.service;

import com.example.demo.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    Optional<Session> getSession(Long id);

    List<Session> getAllSessions();

    Session addSession(Session session);
}
