package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public class WatchlistRepositoryImpl implements WatchlistRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WatchlistMediaRepository watchlistMediaRepository;

    @Override
    public Iterable<Media> findAllMediaById(long id) {
        Iterable<Media> m = null;
        try {
            String query = "SELECT m FROM WatchlistMedia wm JOIN Media m ON wm.mediaId = m.id WHERE wm.watchlistId = :id";
            Query q = em.createQuery(query).setParameter("id", id);
            List<Media> resultList = q.getResultList();
            m = resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void updateWatchlistMedia(WatchlistMedia wm) {
        Optional<WatchlistMedia> optional = watchlistMediaRepository.findById(wm.id)
        try {

            watchlistMediaRepository.save(wm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Watchlist> findAllByUserId(long userId) {
        Iterable<Watchlist> i = null;
        try {
            String query = "SELECT w FROM Watchlist w WHERE w.userId = :userId";
            Query q = em.createQuery(query).setParameter("userId", userId);
            List<Watchlist> resultList = q.getResultList();
            i = resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
}
