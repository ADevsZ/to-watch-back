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
@Table(name = "watchlists")
public class Watchlist {
    private long watchlistId;
    private String name;
    private boolean active;
    private User userId;
}
