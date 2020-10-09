package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "speakers")
@Data
@NoArgsConstructor
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    public Speaker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
