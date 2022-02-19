package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.FavouriteItem;

import java.util.List;

public interface FavouriteService<E,R>  {
    E addFavoriteGroup(E favourite,Long userId);
    List<E> getAllFavoritesGroups(Long userId);

    R addFavoriteItems(R items,Long favGrpId);
    List<R> getAllFavoriteItems(Long favGrpId);
}
