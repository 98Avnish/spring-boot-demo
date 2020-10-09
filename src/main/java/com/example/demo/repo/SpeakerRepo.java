package com.example.demo.repo;

import com.example.demo.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepo extends JpaRepository<Speaker, Long> { }
