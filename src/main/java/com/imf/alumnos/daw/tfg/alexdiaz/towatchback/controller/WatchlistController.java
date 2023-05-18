package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistActiveDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistMediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistPostDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.WatchlistService;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    
    @Autowired
    WatchlistService watchlistService;

    @GetMapping
    public ResponseEntity<Iterable<WatchlistDto>> getAllByUserId(@RequestParam("userId") long id) {
        try {
            return new ResponseEntity<>(watchlistService.findAllWatchlistByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<WatchlistMediaDto>> getAllMediaByWatchlistId(@PathVariable("id") long watchlistId) {
        try {
            return new ResponseEntity<>(this.watchlistService.findAllMediaWatchlistById(watchlistId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody List<WatchlistMediaDto> list) {
        try {
            this.watchlistService.updateWatchlistMedia(list);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> create(@RequestBody WatchlistPostDto watchlistPostDto) {
        try {
            this.watchlistService.createWatchlist(watchlistPostDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this.watchlistService.deleteWatchlist(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<WatchlistActiveDto> getActiveWatchlist(@RequestParam("userId") long userId) {
        try {
            WatchlistActiveDto watchlistActiveDto = this.watchlistService.getActiveWatchlist(userId);
            return new ResponseEntity<>(watchlistActiveDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{watchlistId}/{mediaId}")
    public ResponseEntity<HttpStatus> updateViewedWatchlistMedia(@PathVariable("watchlistId") long watchlistId, @PathVariable("mediaId") long mediaId, @RequestParam("viewed") boolean viewed) {
        try {
            this.watchlistService.updateViewedWatchlistMedia(watchlistId, mediaId, viewed);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/active")
    public ResponseEntity<HttpStatus> updateActiveWatchlist(@PathVariable("id") long id, @RequestParam("active") boolean active) {
        try {
            this.watchlistService.updateActiveWatchlist(id, active);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
