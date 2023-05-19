package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

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
@Table(name = "streaming_platforms")
public class StreamingPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long platformId;
    private String platformName;
}
