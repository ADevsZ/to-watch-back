package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RatingMediaRepositoryImpl implements RatingMediaRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Double calculateAverageRatingMedia(long mediaId) {
        String query = "SELECT AVG(rm.rating) FROM RatingMedia rm WHERE rm.media.id = :mediaId";
        Query q = entityManager.createQuery(query).setParameter("mediaId", mediaId);
        Object resultList = q.getSingleResult();
        return (Double) resultList;
    }
    
}
