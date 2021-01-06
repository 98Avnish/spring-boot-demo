package com.example.demo.service;

import com.example.demo.aspects.AspectLog;
import com.example.demo.model.Session;
import com.example.demo.repo.SessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    private SessionRepo repo;

    public SessionServiceImpl(SessionRepo sessionRepo) {
        log.info("SessionServiceImpl constructor");
        this.repo = sessionRepo;
    }

    @Override
    @AspectLog
    public Optional<Session> getSession(Long id) {
        log.info("Getting Session :{}", id);
        return repo.findById(id);
    }

    @Override
    public List<Session> getAllSessions() {
        log.info("Getting all Sessions");
        return repo.findAll();
    }

    @Override
    public Session addSession(Session session) {
        log.info("Saving Session :{}", session);
        return repo.saveAndFlush(session);
    }

    @Override
    public Session updateSession(Session session) {
        log.info("Updating Session :{}", session);
        return repo.saveAndFlush(session);
    }

    @Override
    public void deleteSession(Long id) {
        log.info("Deleting Session :{}", id);
        repo.deleteById(id);
    }

}
