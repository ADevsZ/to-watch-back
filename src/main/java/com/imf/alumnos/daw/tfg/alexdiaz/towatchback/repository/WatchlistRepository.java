package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long>{
    @Query(value = "SELECT * FROM db_to_watch.watchlists WHERE user_id = :userId", nativeQuery = true)
    Iterable<Watchlist> findByUserId(@Param("userId") long userId);
    @Query(value = "SELECT * FROM db_to_watch.watchlists WHERE user_id = :userId AND active = 1", nativeQuery = true)
    Watchlist findActiveByUserId(@Param("userId") long userId);
}
