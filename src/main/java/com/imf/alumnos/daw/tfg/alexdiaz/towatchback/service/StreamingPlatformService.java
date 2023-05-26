package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.List;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public interface StreamingPlatformService {
    List<StreamingPlatformMediaDto> getAllStreamingPlatformUrlsByMediaId(long mediaId);
}
