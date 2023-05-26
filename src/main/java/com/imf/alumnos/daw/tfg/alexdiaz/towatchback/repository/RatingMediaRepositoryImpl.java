package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    @Override
    public int getRatingMediaByUser(long mediaId, long userId) {
        try {
            String query = "SELECT rm.rating FROM RatingMedia rm WHERE rm.media.id = :mediaId AND rm.user.id = :userId";
            Query q = entityManager.createQuery(query).setParameter("mediaId", mediaId).setParameter("userId", userId);
            Object resultList = q.getSingleResult();
            return (Integer) resultList;
        } catch (NoResultException ex) {
            return 0;
        }
    }
    
    
}
