package com.example.demo.service;

import com.example.demo.model.Session;
import com.example.demo.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
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
        return repo.findById(id);
    }

    @Override
    public List<Session> getAllSessions() {
        return repo.findAll();
    }

    @Override
    public Session addSession(Session session) {
        return repo.saveAndFlush(session);
    }

    @Override
    public Session updateSession(Session session) {
        return repo.saveAndFlush(session);
    }

    @Override
    public void deleteSession(Long id) {
        repo.deleteById(id);
    }

}
