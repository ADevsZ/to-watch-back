package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

public interface WatchlistMediaRepository extends JpaRepository<WatchlistMedia, Long>{
    @Query(value = "SELECT wm FROM WatchlistMedia wm WHERE wm.watchlist.id = :watchlistId ORDER BY wm.orden ASC")
    Iterable<WatchlistMedia> findByIdAndOrdered(@Param("watchlistId") long watchlistId);
    @Query(value = "SELECT wm FROM WatchlistMedia wm WHERE wm.watchlist.id = :watchlistId AND wm.media.id = :mediaId")
    WatchlistMedia findByWatchlistIdAndMediaId(@Param("watchlistId") long watchlistId, @Param("mediaId") long mediaId);
}
