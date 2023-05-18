package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistPostDto {
    private String name;
    private long userId;
    private List<WatchlistMediaPostDto> mediaList;
}
