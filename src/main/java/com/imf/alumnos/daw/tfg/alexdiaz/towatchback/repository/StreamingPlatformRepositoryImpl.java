package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;

public class StreamingPlatformRepositoryImpl implements StreamingPlatformRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StreamingPlatformMediaDto> getAllUrlsStreamingPlatformsByMediaId(long mediaId) {
        String query = "SELECT sp.platformName, spm.url FROM StreamingPlatform sp, StreamingPlatformsMedia spm WHERE sp.platformId = spm.platform.platformId AND spm.media.id = :mediaId ORDER BY sp.platformId ASC";
        Query q = entityManager.createQuery(query).setParameter("mediaId", mediaId);
        List<Object[]> resulList = q.getResultList();

        List<StreamingPlatformMediaDto> list = new ArrayList<>();
        for (Object[] o: resulList) {
            StreamingPlatformMediaDto streamingPlatformMediaDto = new StreamingPlatformMediaDto((String) o[0], (String) o[1]);
            list.add(streamingPlatformMediaDto);
        }
        return list;
    }
    
}
