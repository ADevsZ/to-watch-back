package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public interface StreamingPlatformService {
    Iterable<StreamingPlatformMediaDto> getAllStreamingPlatformUrlsByMediaId(long mediaId);
}
