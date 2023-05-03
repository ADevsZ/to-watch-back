package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    MediaRepository mediaRepository;

    public Optional<Media> findById(Long id) {
        return mediaRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return mediaRepository.existsById(id);
    }

    @GetMapping("/films")
    public Iterable<Media> findAll() {
        return mediaRepository.findAll();
    }

    public long count() {
        return mediaRepository.count();
    }
    
    
}
