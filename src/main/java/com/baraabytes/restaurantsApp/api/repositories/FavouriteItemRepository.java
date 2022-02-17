package com.baraabytes.restaurantsApp.api.repositories;

import com.baraabytes.restaurantsApp.api.entities.Favourite;
import com.baraabytes.restaurantsApp.api.entities.FavouriteItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteItemRepository  extends JpaRepository<FavouriteItem,Long> {
    List<FavouriteItem> findAllByFavouriteId(Long id);
}
