package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;

@Repository
public interface WatchlistRepository extends CrudRepository<Watchlist, Long>{
    
}
