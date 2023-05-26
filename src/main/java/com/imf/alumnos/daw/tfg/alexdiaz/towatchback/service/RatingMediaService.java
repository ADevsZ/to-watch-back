package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

public interface RatingMediaService {
    void createRatingMedia(long mediaId, int rating, String token);
    Double getAverageRatingMedia(long mediaId);
    Integer getRatingMediaByUser(long mediaId, long userId);
}
