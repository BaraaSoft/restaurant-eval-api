package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.Favourite;
import com.baraabytes.restaurantsApp.api.entities.FavouriteItem;
import com.baraabytes.restaurantsApp.api.entities.User;
import com.baraabytes.restaurantsApp.api.repositories.FavouriteItemRepository;
import com.baraabytes.restaurantsApp.api.repositories.FavouriteRepository;
import com.baraabytes.restaurantsApp.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService<Favourite, FavouriteItem> {

    @Autowired
    FavouriteRepository favouriteRepository;
    @Autowired
    FavouriteItemRepository favouriteItemRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Favourite addFavoriteGroup(Favourite favourite, Long userId) {
        User user = userRepository.findById(userId).get();
        favourite.setUser(user);
        return favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourite> getAllFavoritesGroups(Long userId) {
      return favouriteRepository.findAllByUserId(userId);
    }

    @Override
    public List<FavouriteItem> addFavoriteItems(List<FavouriteItem> items,Long favGrpId) {
        Favourite favourite = favouriteRepository.findById(favGrpId).get();
        items.stream().forEach((favItem)-> favItem.setFavourite(favourite));
        return favouriteItemRepository.saveAll(items);
    }

    @Override
    public List<FavouriteItem> getAllFavoriteItems(Long favGrpId) {
        return favouriteItemRepository.findAllByFavouriteId(favGrpId);
    }
}
