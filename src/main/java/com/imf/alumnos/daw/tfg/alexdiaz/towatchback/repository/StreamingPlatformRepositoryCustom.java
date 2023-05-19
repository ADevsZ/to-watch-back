package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public interface StreamingPlatformRepositoryCustom {
    Iterable<StreamingPlatformMediaDto> getAllUrlsStreamingPlatformsByMediaId(long mediaId);
}
