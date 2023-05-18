package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistMediaPostDto {
    private int orden;
    private long mediaId;
}
