package com.baraabytes.restaurantsApp.api.repositories;

import com.baraabytes.restaurantsApp.api.entities.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {
    List<Favourite> findAllByUserId(Long id);
}
