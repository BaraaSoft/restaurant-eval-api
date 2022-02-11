package com.baraabytes.restaurantsApp.api.repositories;


import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAllByNameStartsWith(String name);
}
