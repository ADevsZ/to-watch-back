package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public class StreamingPlatformRepositoryImpl implements StreamingPlatformRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<StreamingPlatformMediaDto> getAllUrlsStreamingPlatformsByMediaId(long mediaId) {
        String query = "SELECT sp.platformName, spm.url FROM StreamingPlatform sp, StreamingPlatformsMedia spm WHERE sp.platformId = spm.platform.platformId AND spm.media.id = :mediaId ORDER BY sp.platformId ASC";
        Query q = entityManager.createQuery(query).setParameter("mediaId", mediaId);
        return q.getResultList();
    }
    
}
