package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.StreamingPlatformRepositoryCustom;

@Service
public class StreamingPlatformServiceImpl implements StreamingPlatformService{

    @Autowired
    private StreamingPlatformRepositoryCustom streamingPlatformRepositoryCustom;

    @Override
    public List<StreamingPlatformMediaDto> getAllStreamingPlatformUrlsByMediaId(long mediaId) {
        return this.streamingPlatformRepositoryCustom.getAllUrlsStreamingPlatformsByMediaId(mediaId);
    }
    
}
