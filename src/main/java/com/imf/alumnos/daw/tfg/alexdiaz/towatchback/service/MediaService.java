package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.MediaDto;

public interface MediaService {
    MediaDto getMediaFindById(long id);
    Iterable<MediaDto> findAllFilms();
    Iterable<MediaDto> findAllSeries();
}
