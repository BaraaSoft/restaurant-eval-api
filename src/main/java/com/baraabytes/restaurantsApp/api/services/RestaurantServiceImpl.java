package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService<Restaurant, Schedule> {



    public RestaurantServiceImpl() {
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant findRestaurant(Long id) {
        return null;
    }

    @Override
    public List<Restaurant> findRestaurants(String name) {
        return null;
    }

    @Override
    public List<Restaurant> findRestaurants(WeekDayType weekDay) {
        return null;
    }

    @Override
    public List<Restaurant> findRestaurants(LocalDateTime openTime, LocalDateTime closeTime) {
        return null;
    }

    @Override
    public void removeRestaurant(Long id) {

    }

    @Override
    public Schedule addTimeEntry(Schedule Schedule) {
        return null;
    }

    @Override
    public void removeTimeEntry(Long id) {

    }
}
