package com.baraabytes.restaurantsApp.api.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class FavouriteItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @OneToOne
    private Restaurant restaurant;

    @ManyToOne
    private Favourite favourite;
    public FavouriteItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourite favourite) {
        this.favourite = favourite;
    }
}
