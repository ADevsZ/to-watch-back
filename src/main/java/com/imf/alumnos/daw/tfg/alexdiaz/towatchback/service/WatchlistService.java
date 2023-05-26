package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.List;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistActiveDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistMediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistPostDto;

public interface WatchlistService {
    Iterable<WatchlistDto> findAllWatchlistByUserId(long userId);
    Iterable<WatchlistMediaDto> findAllMediaWatchlistById(long watchlistId);
    void updateWatchlistMedia(List<WatchlistMediaDto> wm);
    void createWatchlist(WatchlistPostDto watchlistPostDto);
    void deleteWatchlist(long id);
    WatchlistActiveDto getActiveWatchlist(long userId);
    void updateViewedWatchlistMedia(long watchlistId, long mediaId, boolean viewed);
    void updateActiveWatchlist(long watchlistId, boolean active);
    Long countWatchlistMediaActiveNotViewed(long watchlistId);
}
