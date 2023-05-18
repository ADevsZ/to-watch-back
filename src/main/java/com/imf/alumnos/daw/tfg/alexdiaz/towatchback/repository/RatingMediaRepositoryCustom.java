package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingMediaRepositoryCustom {
    @Query(name = "SELECT AVG(rm.rating) FROM RatingMedia rm WHERE rm.media.id = :mediaId")
    Double calculateAverageRatingMedia(@Param("mediaId") long mediaId);
}
