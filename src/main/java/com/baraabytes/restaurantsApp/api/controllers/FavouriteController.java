package com.baraabytes.restaurantsApp.api.controllers;


import com.baraabytes.restaurantsApp.api.entities.Favourite;
import com.baraabytes.restaurantsApp.api.entities.FavouriteItem;
import com.baraabytes.restaurantsApp.api.services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user/{userId}/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService<Favourite, FavouriteItem> favouriteService;

    @PostMapping("")
    public ResponseEntity<Favourite> addFavourite(@PathVariable Long userId,@RequestBody Favourite favourite){
        Favourite favoriteGroup = favouriteService.addFavoriteGroup(favourite, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(favoriteGroup.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(favoriteGroup);
    }

    @GetMapping("")
    public ResponseEntity<List<Favourite>> getFavourite(@PathVariable Long userId){
        List<Favourite> favourites = favouriteService.getAllFavoritesGroups(userId);
        return ResponseEntity.ok(favourites);
    }

    @GetMapping("/{grpId}/items")
    public ResponseEntity<List<FavouriteItem>> getFavouriteItems(@PathVariable Long userId,
                                                                 @PathVariable Long grpId){
        List<FavouriteItem> items = favouriteService.getAllFavoriteItems(grpId);

        return ResponseEntity.ok(items);
    }

    @PostMapping("/{grpId}/items")
    public ResponseEntity<List<FavouriteItem>> addFavouriteItems(@PathVariable Long userId,
                                                                 @PathVariable Long grpId,
                                                                 @RequestBody List<FavouriteItem> favouriteItemList
                                                                 ){
        List<FavouriteItem> items =  favouriteService.addFavoriteItems(favouriteItemList,grpId);
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId.toString());
        params.put("grpId",grpId.toString());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .buildAndExpand(params)
                .toUri();
        return  ResponseEntity.created(uri).body(items);

    }




}
