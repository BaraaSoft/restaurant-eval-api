package com.baraabytes.restaurantsApp.api.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Favourite {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "favourite",fetch = FetchType.LAZY)
    private List<FavouriteItem> items;

    public Favourite() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FavouriteItem> getItems() {
        return items;
    }

    public void setItems(List<FavouriteItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
