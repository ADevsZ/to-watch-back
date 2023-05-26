package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaPremiereDto {
    private long premiereId;
    private String mediaType;
    private String mediaTitle;
    private Date releaseDate;
}
