package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public interface WatchlistRepositoryCustom {
    Iterable<Watchlist> findAllByUserId(long userId);
    Iterable<Media> findAllMediaById(long id);
    void updateWatchlistMedia(WatchlistMedia wm);
}
