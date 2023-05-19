package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.MediaPremiere;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.MediaPremiereDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaPremiereRepository;

@Service
public class MediaPremiereServiceImpl implements MediaPremiereService{

    @Autowired
    MediaPremiereRepository mediaPremiereRepository;

    @Override
    public Iterable<MediaPremiereDto> getAllMediaPremiere() {
        Iterable<MediaPremiere> iterable = this.mediaPremiereRepository.findAll();
        List<MediaPremiereDto> list = new ArrayList<>();
        for (MediaPremiere mediaPremiere: iterable) {
            MediaPremiereDto mediaPremiereDto = new MediaPremiereDto(mediaPremiere.getPremiereId(), mediaPremiere.getMediaType(), mediaPremiere.getMediaTitle(), mediaPremiere.getReleaseDate());
            list.add(mediaPremiereDto);
        }
        return list;
    }
}
