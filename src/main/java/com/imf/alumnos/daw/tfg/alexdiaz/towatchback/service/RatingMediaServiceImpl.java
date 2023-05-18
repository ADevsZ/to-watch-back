package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.RatingMedia;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.RatingMediaRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.RatingMediaRepositoryCustom;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;

@Service
public class RatingMediaServiceImpl implements RatingMediaService{
    @Autowired
    private RatingMediaRepository ratingMediaRepository;

    @Autowired
    private RatingMediaRepositoryCustom ratingMediaRepositoryCustom;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void createRatingMedia(long mediaId, int rating, String token) {
        long newRatingId = this.ratingMediaRepository.count() + 1;
        long userId = userServiceImpl.getUserIdByToken(token);

        Optional<Media> media = this.mediaRepository.findById(mediaId);
        Optional<User> user = this.userRepository.findById(userId);

        if (media.isPresent() && user.isPresent()) {
            RatingMedia ratingMedia = new RatingMedia(newRatingId, rating, media.get(), user.get());
            this.ratingMediaRepository.save(ratingMedia);
        }
    }

    @Override
    public Double getAverageRatingMedia(long mediaId) {
        return this.ratingMediaRepositoryCustom.calculateAverageRatingMedia(mediaId);
    }
}
