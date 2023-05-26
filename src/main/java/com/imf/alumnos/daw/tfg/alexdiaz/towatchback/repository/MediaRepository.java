package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;

public interface MediaRepository extends CrudRepository<Media, Long>{
    @Query("SELECT m FROM Media m WHERE m.type = :type ORDER BY m.title ASC")
    List<Media> findAllByType(@Param("type") String type);
}
