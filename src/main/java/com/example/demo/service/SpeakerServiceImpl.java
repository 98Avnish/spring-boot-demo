package com.example.demo.service;

import com.example.demo.model.Speaker;
import com.example.demo.repo.SpeakerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepo repo;

    @PostConstruct
    private void addData() {
        repo.saveAll(Arrays.asList(
                new Speaker("Robert", "1"),
                new Speaker("Brian", "2"),
                new Speaker("Charles", "3")
        ));
    }

    @Override
    public Optional<Speaker> getSpeaker(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Speaker> getAllSpeakers() {
        return repo.findAll();
    }

    @Override
    public Speaker addSpeaker(Speaker speaker) {
        return repo.saveAndFlush(speaker);
    }
}
