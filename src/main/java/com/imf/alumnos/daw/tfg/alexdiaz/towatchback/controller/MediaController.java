package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.MediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.MediaPremiereDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.StreamingPlatformMediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.MediaPremiereService;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.MediaService;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.StreamingPlatformService;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @Autowired
    StreamingPlatformService streamingPlatformService;

    @Autowired
    MediaPremiereService mediaPremiereService;

    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> findById(@PathVariable("id") Long id) {
        try {
            MediaDto mDto = this.mediaService.getMediaFindById(id);
            return new ResponseEntity<MediaDto>(mDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/films")
    public ResponseEntity<Iterable<MediaDto>> findAllFilms() {
        try {
            Iterable<MediaDto> lIterable = this.mediaService.findAllFilms();
            return new ResponseEntity<>(lIterable, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/series")
    public ResponseEntity<Iterable<MediaDto>> findAllSeries() {
        try {
            Iterable<MediaDto> lIterable = this.mediaService.findAllSeries();
            return new ResponseEntity<>(lIterable, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{mediaId}/platforms")
    public ResponseEntity<Iterable<StreamingPlatformMediaDto>> getAllPlatformsByMedia(@PathVariable("mediaId") long mediaId) {
        try {
            Iterable<StreamingPlatformMediaDto> listUrl = this.streamingPlatformService.getAllStreamingPlatformUrlsByMediaId(mediaId);
            return new ResponseEntity<>(listUrl, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/premieres")
    public ResponseEntity<Iterable<MediaPremiereDto>> getAllMediaPremieres() {
        try {
            Iterable<MediaPremiereDto> lisIterable = this.mediaPremiereService.getAllMediaPremiere();
            return new ResponseEntity<>(lisIterable, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
