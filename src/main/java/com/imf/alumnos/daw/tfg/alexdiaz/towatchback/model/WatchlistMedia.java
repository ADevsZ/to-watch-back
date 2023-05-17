package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "watchlists_x_media")
public class WatchlistMedia {
    private Watchlist watchlistId;
    private Media mediaId;
    private int order;
    private boolean viewed;
}
