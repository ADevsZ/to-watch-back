package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.WatchlistServiceImpl;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    
    @Autowired
    WatchlistServiceImpl watchlistServiceImpl;

    @GetMapping("/")
    public Iterable<Watchlist> getAllByUserId(@RequestParam("userId") long id) {
        return watchlistServiceImpl.getAllByUserId(id);
    }

    @GetMapping("/{id}")
    public Iterable<Media> getAllMediaByWatchlistId(@PathParam("id") long id) {
        return watchlistServiceImpl.getAllMediaByWatchlistId(id);
    }

    @PutMapping("/{id}")
    public Iterable<WatchlistMedia> update(@PathParam("id") long id, @RequestBody Iterable<WatchlistMedia> list) {
        
    }
}
