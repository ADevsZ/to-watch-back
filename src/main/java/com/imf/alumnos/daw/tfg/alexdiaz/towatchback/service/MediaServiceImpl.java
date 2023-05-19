package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.MediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;

@Service
public class MediaServiceImpl implements MediaService{
    @Autowired
    MediaRepository mediaRepository;

    @Override
    public MediaDto getMediaFindById(long id) {
        Optional<Media> optional = this.mediaRepository.findById(id);
        MediaDto mDto = null;
        if (optional.isPresent()) {
            mDto = new MediaDto(optional.get().getType(), optional.get().getTitle(), 
            optional.get().getReleaseDate(), optional.get().getNationality(), 
            optional.get().getSynopsis(), optional.get().getDuration(), 
            optional.get().getAverageDuration(), optional.get().getSessionsNumber(), 
            optional.get().getEpisodesNumber());
        }
        return mDto;
    }

    @Override
    public Iterable<MediaDto> findAllFilms() {
        Iterable<Media> lIterable = this.mediaRepository.findAllByType("Film");
        List<MediaDto> mIterable = new ArrayList<>();

        for (Media m: lIterable) {
            MediaDto mDto = new MediaDto(m.getType(), m.getTitle(), m.getReleaseDate(), m.getNationality(), 
            m.getSynopsis(), m.getDuration(), m.getAverageDuration(), m.getSessionsNumber(), m.getEpisodesNumber());
            mIterable.add(mDto);
        }

        return mIterable;
    }

    @Override
    public Iterable<MediaDto> findAllSeries() {
        Iterable<Media> lIterable = this.mediaRepository.findAllByType("Serie");
        List<MediaDto> mIterable = new ArrayList<>();

        for (Media m: lIterable) {
            MediaDto mDto = new MediaDto(m.getType(), m.getTitle(), m.getReleaseDate(), m.getNationality(), 
            m.getSynopsis(), m.getDuration(), m.getAverageDuration(), m.getSessionsNumber(), m.getEpisodesNumber());
            mIterable.add(mDto);
        }

        return mIterable;
    }
}