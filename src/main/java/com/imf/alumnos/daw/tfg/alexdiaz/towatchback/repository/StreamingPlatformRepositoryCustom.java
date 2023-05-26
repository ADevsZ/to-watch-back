package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.List;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public interface StreamingPlatformRepositoryCustom {
    List<StreamingPlatformMediaDto> getAllUrlsStreamingPlatformsByMediaId(long mediaId);
}
