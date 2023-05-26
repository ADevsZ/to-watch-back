package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.RatingMedia;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogsEnum;
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
            if (media.get().getType().equals("Film")) {
                this.userServiceImpl.createUserLog(this.userServiceImpl.construirUserLog("TW03", user.get().getLoginName() + UserLogsEnum.PELICULA_VALORADA.label + media.get().getTitle(), new Date(), null, userId));
            } else {
                this.userServiceImpl.createUserLog(this.userServiceImpl.construirUserLog("TW04", user.get().getLoginName() + UserLogsEnum.SERIE_VALORADA.label + media.get().getTitle(), new Date(), null, userId));
            }
        }
    }

    @Override
    public Double getAverageRatingMedia(long mediaId) {
        return this.ratingMediaRepositoryCustom.calculateAverageRatingMedia(mediaId);
    }

    @Override
    public Integer getRatingMediaByUser(long mediaId, long userId) {
        return this.ratingMediaRepositoryCustom.getRatingMediaByUser(mediaId, userId);
    }
}
