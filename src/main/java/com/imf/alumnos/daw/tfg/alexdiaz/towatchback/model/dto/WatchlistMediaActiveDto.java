package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistMediaActiveDto {
    private long mediaId;
    private String type;
    private int orden;
    private boolean viewed;
    private String mediaTitle;
}
