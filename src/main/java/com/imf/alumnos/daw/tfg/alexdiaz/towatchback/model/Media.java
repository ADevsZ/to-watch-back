package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
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
public class Media{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String type;
    @Basic
    private String title;
    @Basic
    @Column(name = "release_date")
    private Date releaseDate;
    @Basic
    private String nationality;
    @Basic
    private String synopsis;
    @Basic
    private int duration;
    @Column(name = "average_duration")
    private int averageDuration;
    @Column(name = "sessions_number")
    private int sessionsNumber;
    @Column(name = "episodes_number")
    private int episodesNumber;
}
