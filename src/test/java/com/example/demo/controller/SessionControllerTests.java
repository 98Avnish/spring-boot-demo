package com.example.demo.controller;

import com.example.demo.model.Session;
import com.example.demo.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SessionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService service;

    private static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GET /conferenceData/sessions/1 - Success")
    void testGetSession() throws Exception {
        Session session = new Session();
        session.setId(1L);
        session.setDescription("Session");

        when(service.getSession(anyLong()))
                .thenReturn(Optional.of(session));

        mockMvc.perform(get("/conferenceData/sessions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(session.getDescription()));
    }

    @Test
    @DisplayName("GET /conferenceData/sessions/1 - Not Found")
    void testGetSessionNotFound() throws Exception {
        when(service.getSession(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/conferenceData/sessions/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /conferenceData/sessions - Success")
    void testGetSessions() throws Exception {
        when(service.getAllSessions())
                .thenReturn(Arrays.asList(
                        new Session("1"),
                        new Session("2")
                ));

        mockMvc.perform(get("/conferenceData/sessions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("1"));
    }

    @Test
    @DisplayName("POST /conferenceData/sessions - Success")
    void testAddSession() throws Exception {
        Session postSession = new Session("Session");
        Session savedSession = new Session("Session");
        savedSession.setId(1L);

        when(service.addSession(any()))
                .thenReturn(savedSession);
        when(service.getSession(anyLong()))
                .thenReturn(Optional.of(savedSession));

        mockMvc.perform(post("/conferenceData/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSession))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(savedSession.getDescription()));
    }

    @Test
    @DisplayName("POST /conferenceData/sessions - Failure")
    void testAddSessionFailure() throws Exception {
        Session postSession = new Session("");

        mockMvc.perform(post("/conferenceData/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSession))
        ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(postSession.getDescription()));
    }

    @Test
    @DisplayName("PUT /conferenceData/sessions/1 - Success")
    void testUpdateSession() throws Exception {
        Session postSession = new Session("Updated Session");
        postSession.setId(1L);
        Session savedSession = new Session("Session");
        savedSession.setId(1L);

        when(service.getSession(anyLong()))
                .thenReturn(Optional.of(savedSession));
        when(service.updateSession(any()))
                .thenReturn(postSession);

        mockMvc.perform(put("/conferenceData/sessions/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSession))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(postSession.getDescription()));
    }

    @Test
    @DisplayName("PUT /conferenceData/sessions/1 - Failure")
    void testUpdateSessionFailure() throws Exception {
        Session postSession = new Session("");
        postSession.setId(1L);

        mockMvc.perform(put("/conferenceData/sessions/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSession))
        ).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(postSession.getDescription()));
    }

    @Test
    @DisplayName("PUT /conferenceData/sessions/1 - Not Found")
    void testUpdateSessionNotFound() throws Exception {
        Session postSession = new Session("Updated Session");
        postSession.setId(1L);

        when(service.getSession(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/conferenceData/sessions/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSession))
        ).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /conferenceData/sessions/1 - Success")
    void testDeleteSession() throws Exception {
        when(service.getSession(anyLong()))
                .thenReturn(Optional.of(new Session("1")));

        mockMvc.perform(delete("/conferenceData/sessions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    @DisplayName("DELETE /conferenceData/sessions/1 - Not Found")
    void testDeleteSessionNotFound() throws Exception {
        when(service.getSession(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/conferenceData/sessions/{id}", 1))
                .andExpect(status().isNotFound());
    }

}
