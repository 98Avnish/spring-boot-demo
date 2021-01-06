package com.example.demo.controller;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Speaker;
import com.example.demo.service.SpeakerService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SpeakerControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpeakerService service;

    private String asJsonString(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GET /conferenceData/speakers/1 - Success")
    void testGetSpeaker() throws Exception {
        Speaker speaker = new Speaker();
        speaker.setId(1L);
        speaker.setFirstName("Speaker");
        speaker.setLastName("123");

        when(service.getSpeaker(anyLong()))
                .thenReturn(speaker);

        mockMvc.perform(get("/conferenceData/speakers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(speaker.getFirstName()));

        verify(service, times(1)).getSpeaker(anyLong());
    }

    @Test
    @DisplayName("GET /conferenceData/speakers/1 - Not Found")
    void testGetSpeakerNotFound() throws Exception {
        when(service.getSpeaker(anyLong()))
                .thenThrow(new NotFoundException("Speaker not found"));

        mockMvc.perform(get("/conferenceData/speakers/{id}", 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /conferenceData/speakers - Success")
    void testGetSpeakers() throws Exception {
        when(service.getAllSpeakers())
                .thenReturn(Arrays.asList(
                        new Speaker("1", "1"),
                        new Speaker("2", "2")
                ));

        mockMvc.perform(get("/conferenceData/speakers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("1"));
    }

    @Test
    @DisplayName("GET /conferenceData/speakers - Failure")
    void testGetSpeakersFailure() throws Exception {
        when(service.getAllSpeakers())
                .thenThrow(new RuntimeException("Error getting speakers"));

        mockMvc.perform(get("/conferenceData/speakers"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /conferenceData/speakers - Success")
    void testAddSpeaker() throws Exception {
        Speaker postSpeaker = new Speaker("Speaker", "123");
        Speaker savedSpeaker = new Speaker("Speaker", "123");
        savedSpeaker.setId(1L);

        when(service.addSpeaker(any()))
                .thenReturn(savedSpeaker);

        mockMvc.perform(post("/conferenceData/speakers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(savedSpeaker.getFirstName()));
    }

    @Test
    @DisplayName("POST /conferenceData/speakers - Validation Failure")
    void testAddSpeakerValidationFailure() throws Exception {
        Speaker postSpeaker = new Speaker("Speaker", "123");

        when(service.addSpeaker(any()))
                .thenThrow(new RuntimeException("Error saving speaker"));

        mockMvc.perform(post("/conferenceData/speakers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /conferenceData/speakers - Failure")
    void testAddSpeakerFailure() throws Exception {
        Speaker postSpeaker = new Speaker("", "");

        mockMvc.perform(post("/conferenceData/speakers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /conferenceData/speakers/1 - Success")
    void testUpdateSpeaker() throws Exception {
        Speaker postSpeaker = new Speaker("Updated Speaker", "123");
        postSpeaker.setId(1L);
        Speaker savedSpeaker = new Speaker("Speaker", "123");
        savedSpeaker.setId(1L);
        
        when(service.updateSpeaker(any()))
                .thenReturn(postSpeaker);

        mockMvc.perform(put("/conferenceData/speakers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(postSpeaker.getFirstName()));
    }

    @Test
    @DisplayName("PUT /conferenceData/speakers/1 - Validation Failure")
    void testUpdateSpeakerFailure() throws Exception {
        Speaker postSpeaker = new Speaker("", "");
        postSpeaker.setId(1L);

        mockMvc.perform(put("/conferenceData/speakers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /conferenceData/speakers/1 - Not Found")
    void testUpdateSpeakerNotFound() throws Exception {
        Speaker postSpeaker = new Speaker("Updated Speaker", "123");
        postSpeaker.setId(1L);

        when(service.updateSpeaker(any()))
                .thenThrow(new NotFoundException("Speaker not found"));

        mockMvc.perform(put("/conferenceData/speakers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postSpeaker))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /conferenceData/speakers/1 - Success")
    void testDeleteSpeaker() throws Exception {
        doNothing()
                .when(service).deleteSpeaker(anyLong());

        mockMvc.perform(delete("/conferenceData/speakers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    @DisplayName("DELETE /conferenceData/speakers/1 - Not Found")
    void testDeleteSpeakerNotFound() throws Exception {
        doThrow(new NotFoundException("Speaker not found"))
                .when(service).deleteSpeaker(anyLong());

        mockMvc.perform(delete("/conferenceData/speakers/{id}", 1))
                .andExpect(status().isBadRequest());
    }
    
}
