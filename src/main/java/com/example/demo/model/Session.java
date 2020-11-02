package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "SESSIONS")
@Data
@NoArgsConstructor
public class Session {

    @Id
    @Column(name = "SESSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;

    public Session(String description) {
        this.description = description;
    }

}
