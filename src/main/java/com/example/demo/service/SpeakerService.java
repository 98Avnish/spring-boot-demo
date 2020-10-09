package com.example.demo.service;

import com.example.demo.model.Speaker;

import java.util.List;
import java.util.Optional;

public interface SpeakerService {

    Optional<Speaker> getSpeaker(Long id);

    List<Speaker> getAllSpeakers();

    Speaker addSpeaker(Speaker speaker);

    Speaker updateSpeaker(Speaker speaker);

    void deleteSpeaker(Long id);
}
