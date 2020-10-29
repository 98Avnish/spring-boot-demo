package com.example.demo.service;

import com.example.demo.model.Session;
import com.example.demo.repo.SessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepo repo;

    @PostConstruct
    private void addData() {
        repo.saveAll(Arrays.asList(
                new Session("Session 1"),
                new Session("Session 2"),
                new Session("Session 3")
        ));
    }

    @Override
    public Optional<Session> getSession(Long id) {
        log.info("Getting Session by id :{}", id);
        return repo.findById(id);
    }

    @Override
    @Cacheable("sessions")
    public List<Session> getAllSessions() {
        log.info("Getting All Sessions");
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
