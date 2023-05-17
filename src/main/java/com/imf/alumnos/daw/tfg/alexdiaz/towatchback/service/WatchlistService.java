package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public interface WatchlistService {
    Iterable<Watchlist> getAllByUserId(long userId);
    Iterable<Media> getAllMediaByWatchlistId(long watchlistId);
    Iterable<WatchlistMedia> updateWatchlist(long id, Iterable<WatchlistMedia> list);
}
