package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistActiveDto {
    private long watchlistId;
    private String name;
    private List<WatchlistMediaActiveDto> mediaList;
}
