package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "media")
public class Media implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String title;
    private Date releaseDate;
    private String nationality;
    private String synopsis;
    private int duration;
    private int averageDuration;
    private int sessionsNumber;
    private int episodesNumber;
}
