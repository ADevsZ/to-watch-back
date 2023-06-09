package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Media;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogsEnum;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.Watchlist;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.WatchlistMedia;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistActiveDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistMediaActiveDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistMediaDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistMediaPostDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.WatchlistPostDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.MediaRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.WatchlistMediaRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.WatchlistRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.WatchlistRepositoryCustom;

@Service
public class WatchlistServiceImpl implements WatchlistService{
    @Autowired
    private WatchlistRepositoryCustom watchlistRepositoryCustom;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private WatchlistMediaRepository watchlistMediaRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Iterable<WatchlistDto> findAllWatchlistByUserId(long userId) {
        Iterable<Watchlist> iterable = this.watchlistRepositoryCustom.findAllWatchlistByUserId(userId);
        List<WatchlistDto> iterableDto = new ArrayList<>();
        for (Watchlist w: iterable) {
            List<WatchlistMedia> lMedias = this.watchlistMediaRepository.findByIdAndOrdered(w.getWatchlistId());
            long countMedias = lMedias.size();
            WatchlistDto wDto = new WatchlistDto(w.getWatchlistId(), w.getName(), w.isActive(), countMedias);
            iterableDto.add(wDto);
        }
        return iterableDto;
    }

    @Override
    public Iterable<WatchlistMediaDto> findAllMediaWatchlistById(long watchlistId) {
        Iterable<WatchlistMedia> iterable = this.watchlistRepositoryCustom.findAllMediaWatchlistById(watchlistId);
        List<WatchlistMediaDto> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        for (WatchlistMedia wm: iterable) {
            Optional<Watchlist> watchlist = this.watchlistRepository.findById(wm.getWatchlist().getWatchlistId());
            Optional<Media> media = this.mediaRepository.findById(wm.getMedia().getId());

            if (watchlist.isPresent() && media.isPresent()) {
                WatchlistMediaDto wMediaDto = new WatchlistMediaDto(wm.getId(), wm.getOrden(), wm.isViewed(), watchlist.get().getWatchlistId(), media.get().getId(), media.get().getType(), media.get().getTitle(), sdf.format(media.get().getReleaseDate()));
                list.add(wMediaDto);
            }
        }
        return list;
    }

    @Override
    public void updateWatchlistMedia(List<WatchlistMediaDto> wm) {
        for (WatchlistMediaDto wMediaDto: wm) {
            Optional<Watchlist> watchlist = this.watchlistRepository.findById(wMediaDto.getWatchlistId());
            Optional<Media> media = this.mediaRepository.findById(wMediaDto.getMediaId());

            if (watchlist.isPresent() && media.isPresent()) {
                WatchlistMedia watchlistMedia = new WatchlistMedia(wMediaDto.getId(), wMediaDto.getOrden(), wMediaDto.isViewed(), watchlist.get(), media.get());
                this.watchlistRepositoryCustom.updateWatchlistMedia(watchlistMedia);
            }
        }
    }

    @Override
    public void createWatchlist(WatchlistPostDto watchlistPostDto) {
        long newWatchlistId = this.watchlistRepository.count() + 1;
        Optional<User> user = this.userRepository.findById(watchlistPostDto.getUserId());
        if (user.isPresent()) {
            Watchlist watchlist = new Watchlist(newWatchlistId, watchlistPostDto.getName(), false, user.get());

            List<WatchlistMedia> wIterable = new ArrayList<>();
            long newWatchlistMediaId = this.watchlistMediaRepository.count();

            for (WatchlistMediaPostDto wDto: watchlistPostDto.getMediaList()) {
                newWatchlistMediaId += 1;
                Optional<Media> media = this.mediaRepository.findById(wDto.getMediaId());
                if (media.isPresent()) {
                    WatchlistMedia wMedia = new WatchlistMedia(newWatchlistMediaId, wDto.getOrden(), false, watchlist, media.get());
                    wIterable.add(wMedia);
                }
            }
        
            this.watchlistRepository.save(watchlist);
            this.watchlistMediaRepository.saveAll(wIterable);
        }
    }

    @Override
    public void deleteWatchlist(long id) {
        Optional<Watchlist> watchlist = this.watchlistRepository.findById(id);
        if (watchlist.isPresent()) {
            this.watchlistRepository.delete(watchlist.get());

            this.userService.createUserLog(this.userService.construirUserLog("TW08", (watchlist.get().getUser().getLoginName() + UserLogsEnum.WATCHLIST_ELIMINADA.label + watchlist.get().getName()), new Date(), null, watchlist.get().getUser().getId()));
        }
    }

    @Override
    public WatchlistActiveDto getActiveWatchlist(long userId) {
        Watchlist watchlist = this.watchlistRepository.findActiveByUserId(userId);
        WatchlistActiveDto watchlistActiveDto = null;
        if (watchlist != null) {
            List<WatchlistMedia> lMedias = this.watchlistMediaRepository.findByIdAndOrdered(watchlist.getWatchlistId());
            String mediaTitle = null;
            String type = null;

            List<WatchlistMediaActiveDto> list = new ArrayList<>();
            for (WatchlistMedia wMedia: lMedias) {
                Optional<Media> media = this.mediaRepository.findById(wMedia.getMedia().getId());
                if (media.isPresent()) {
                    mediaTitle = media.get().getTitle();
                    type = media.get().getType();
                }
                WatchlistMediaActiveDto watchlistMediaActiveDto = new WatchlistMediaActiveDto(wMedia.getMedia().getId(), type, wMedia.getOrden(), wMedia.isViewed(), mediaTitle);
                list.add(watchlistMediaActiveDto);
            }

            watchlistActiveDto = new WatchlistActiveDto(watchlist.getWatchlistId(), watchlist.getName(), list);
        }
        return watchlistActiveDto;
    }

    @Override
    public void updateActiveWatchlist(long watchlistId, boolean active) {
        Optional<Watchlist> watchlist = this.watchlistRepository.findById(watchlistId);
        if (watchlist.isPresent()) {
            Watchlist w = watchlist.get();
            w.setActive(active);

            this.watchlistRepository.save(w);

            long countMediaViewed = this.watchlistMediaRepository.countWatchlistMediaActiveNotViewed(watchlistId);
            if (countMediaViewed == 0) {
                this.userService.createUserLog(this.userService.construirUserLog("TW07", (watchlist.get().getUser().getLoginName() + UserLogsEnum.WATCHLIST_COMPLETADA.label + w.getName()), new Date(), null, watchlist.get().getUser().getId()));
            } else {
                if (active) {
                    this.userService.createUserLog(this.userService.construirUserLog("TW09", (watchlist.get().getUser().getLoginName() + UserLogsEnum.WATCHLIST_ACTIVADA.label + watchlist.get().getName()), new Date(), null, watchlist.get().getUser().getId()));
                } else {
                    this.userService.createUserLog(this.userService.construirUserLog("TW10", (watchlist.get().getUser().getLoginName() + UserLogsEnum.WATCHLIST_DESACTIVADA.label + watchlist.get().getName()), new Date(), null, watchlist.get().getUser().getId()));
                }
            }
        }
    }

    @Override
    public void updateViewedWatchlistMedia(long watchlistId, long mediaId, boolean viewed) {
        WatchlistMedia wMedia = this.watchlistMediaRepository.findByWatchlistIdAndMediaId(watchlistId, mediaId);
        wMedia.setViewed(viewed);
        this.watchlistMediaRepository.save(wMedia);

        Optional<Media> media = this.mediaRepository.findById(mediaId);
        Optional<Watchlist> watchlist = this.watchlistRepository.findById(watchlistId);
        if (media.isPresent() && watchlist.isPresent()) {
            if (media.get().getType().equals("Film")) {
                this.userService.createUserLog(this.userService.construirUserLog("TW05", (watchlist.get().getUser().getLoginName() + UserLogsEnum.PELICULA_VISTA.label + media.get().getTitle()), new Date(), null, watchlist.get().getUser().getId()));
            } else {
                this.userService.createUserLog(this.userService.construirUserLog("TW06", (watchlist.get().getUser().getLoginName() + UserLogsEnum.SERIE_VISTA.label + media.get().getTitle()), new Date(), null, watchlist.get().getUser().getId()));
            }
        }
    }

    @Override
    public Long countWatchlistMediaActiveNotViewed(long watchlistId) {
        return this.watchlistMediaRepository.countWatchlistMediaActiveNotViewed(watchlistId);
    }
    
}