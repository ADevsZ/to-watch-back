package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.WatchlistRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.WatchlistRepositoryImpl;

@Service
public class WatchlistServiceImpl implements WatchlistService{
    @Autowired
    WatchlistRepositoryImpl watchlistRepositoryImpl;

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Iterable<Watchlist> getAllByUserId(long userId) {
        return this.watchlistRepositoryImpl.findAllByUserId(userId);
    }

    @Override
    public Iterable<Media> getAllMediaByWatchlistId(long watchlistId) {
        return this.watchlistRepositoryImpl.findAllMediaById(watchlistId);
    }

    @Override
    public void updateWatchlist(long id, Iterable<WatchlistMedia> list) {
        for (WatchlistMedia wm: list) {
            watchlistRepositoryImpl.updateWatchlistMedia(wm);
        }
    }
    
}
