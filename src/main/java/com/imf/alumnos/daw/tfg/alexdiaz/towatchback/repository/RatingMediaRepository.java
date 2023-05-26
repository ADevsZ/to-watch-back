package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.RatingMedia;

public interface RatingMediaRepository extends JpaRepository<RatingMedia, Long>{
}
