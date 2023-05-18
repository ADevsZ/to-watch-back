package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {
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
