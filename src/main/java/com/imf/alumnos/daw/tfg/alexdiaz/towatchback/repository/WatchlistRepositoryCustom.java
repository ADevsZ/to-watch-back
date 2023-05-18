package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public interface WatchlistRepositoryCustom {
    Iterable<Watchlist> findAllWatchlistByUserId(long userId);
    Iterable<WatchlistMedia> findAllMediaWatchlistById(long watchlistId);
    void updateWatchlistMedia(WatchlistMedia wm);
}
