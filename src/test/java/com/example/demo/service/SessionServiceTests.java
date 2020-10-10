package com.example.demo.service;

import com.example.demo.model.Session;
import com.example.demo.repo.SessionRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class SessionServiceTests {

    @MockBean
    private SessionRepo repo;

    @Autowired
    private SessionService service;

    @Test
    @DisplayName("Get session by id - SUCCESS")
    void testGetSession() {
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(new Session("Session")));

        Optional<Session> session = service.getSession(1L);
        assertTrue(session.isPresent());
        assertEquals("Session", session.get().getDescription());
    }

    @Test
    @DisplayName("Get session by id - NOT FOUND")
    void testGetSessionNotFound() {
        when(repo.findById(anyLong()))
                .thenReturn(Optional.empty());

        Optional<Session> session = service.getSession(1L);
        assertFalse(session.isPresent());
    }

    @Test
    @DisplayName("Get all sessions - SUCCESS")
    void testGetAllSessions() {
        when(repo.findAll())
                .thenReturn(Arrays.asList(
                        new Session("1"),
                        new Session("2")
                ));

        List<Session> sessions = service.getAllSessions();
        assertEquals(2, sessions.size());
        assertEquals("1", sessions.get(0).getDescription());
    }
}
