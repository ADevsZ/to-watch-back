package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.RatingMediaServiceImpl;

@RestController
@RequestMapping("/api/media/rating")
public class RatingMediaController {
    @Autowired
    private RatingMediaServiceImpl  ratingMediaServiceImpl;
    
    @PostMapping("/{mediaId}")
    public ResponseEntity<HttpStatus> createRatingMedia(@PathVariable("mediaId") long mediaId, @RequestParam("rating") int rating, @RequestParam("token") String token) {
        try {
            this.ratingMediaServiceImpl.createRatingMedia(mediaId, rating, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<Double> getAverageRatingMedia(@PathVariable("mediaId") long mediaId) {
        try {
            Double averageRating = this.ratingMediaServiceImpl.getAverageRatingMedia(mediaId);
            return new ResponseEntity<>(averageRating, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
