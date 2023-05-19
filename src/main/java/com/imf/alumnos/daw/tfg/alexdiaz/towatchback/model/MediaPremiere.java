package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

import java.sql.Date;

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
@Table(name = "media_premieres")
public class MediaPremiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long premiereId;
    private String mediaType;
    private String mediaTitle;
    private Date releaseDate;
}
