package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "streaming_platforms_x_media")
public class StreamingPlatformsMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private StreamingPlatform platform;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
    private String url;
}
