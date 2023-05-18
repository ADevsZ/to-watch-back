package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "watchlists_x_media")
public class WatchlistMedia{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int orden;
    @Basic
    private boolean viewed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;
}
