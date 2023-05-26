package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public class WatchlistRepositoryImpl implements WatchlistRepositoryCustom{

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private WatchlistMediaRepository watchlistMediaRepository;

    @Override
    public void updateWatchlistMedia(WatchlistMedia wm) {
        Optional<WatchlistMedia> watchlistMedia = this.watchlistMediaRepository.findById(wm.getId());
        try {
            if (watchlistMedia.isPresent()) {
                WatchlistMedia watchlistMediaNew = watchlistMedia.get();

                watchlistMediaNew.setId(wm.getId());
                watchlistMediaNew.setOrden(wm.getOrden());
                watchlistMediaNew.setViewed(wm.isViewed());
                watchlistMediaNew.setMedia(wm.getMedia());
                watchlistMediaNew.setWatchlist(wm.getWatchlist());

                this.watchlistMediaRepository.save(watchlistMediaNew);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Watchlist> findAllWatchlistByUserId(long userId) {
        Iterable<Watchlist> wIterable = null;
        try {
            wIterable = this.watchlistRepository.findByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wIterable;
    }

    @Override
    public Iterable<WatchlistMedia> findAllMediaWatchlistById(long watchlistId) {
        List<WatchlistMedia> wIterable = null;
        try {
            wIterable = this.watchlistMediaRepository.findByIdAndOrdered(watchlistId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wIterable;
    }
}
