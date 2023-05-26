package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistMediaDto {
    private long id;
    private int orden;
    private boolean viewed;
    private long watchlistId;
    private long mediaId;
    private String type;
    private String mediaTitle;
    private String releaseDate;
}
