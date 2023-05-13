package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    MediaRepository mediaRepository;

    @GetMapping("/{id}")
    public Optional<Media> findById(@PathVariable("id") Long id) {
        System.out.println(mediaRepository.findById(id));
        return mediaRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return mediaRepository.existsById(id);
    }

    @GetMapping("/films")
    public Iterable<Media> findAllFilms() {
        return mediaRepository.findAllByType("Film");
    }

    @GetMapping("/series")
    public Iterable<Media> findAllSeries() {
        return mediaRepository.findAllByType("Serie");
    }

    public long count() {
        return mediaRepository.count();
    }
    
    
}
