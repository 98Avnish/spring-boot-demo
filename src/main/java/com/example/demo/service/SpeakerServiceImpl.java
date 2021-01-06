package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Speaker;
import com.example.demo.repo.SpeakerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SpeakerServiceImpl implements SpeakerService {

    private final SpeakerRepo repo;

    public SpeakerServiceImpl(SpeakerRepo repo) {
        this.repo = repo;
    }

    @Override
    public Speaker getSpeaker(Long id) {
        log.info("Getting Speaker :{}", id);
        Optional<Speaker> speaker = repo.findById(id);
        if (speaker.isPresent()) {
            return speaker.get();
        } else {
            log.error("ERROR - Speaker Not Found :{}", id);
            throw new NotFoundException("Speaker Not Found");
        }
    }

    //TODO: Add caching
    @Override
    public List<Speaker> getAllSpeakers() {
        log.info("Getting all Speakers");
        return repo.findAll();
    }

    @Override
    public Speaker addSpeaker(Speaker speaker) {
        log.info("Saving Speaker :{}", speaker);
        return repo.saveAndFlush(speaker);
    }

    @Override
    public Speaker updateSpeaker(Speaker speaker) {
        Speaker old = getSpeaker(speaker.getId());
        log.info("Updating Speaker(found) :{}", old);
        return repo.saveAndFlush(speaker);
    }

    @Override
    public void deleteSpeaker(Long id) {
        log.info("Deleting Speaker :{}", id);
        repo.deleteById(id);
    }
}
