package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.repositories.RestaurantRepository;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantService<R,E> {

    R addRestaurant(R restaurant);
    R findRestaurant(Long id);
    List<R> allRestaurants();
    List<R> findRestaurants(String name);
    List<R> findRestaurants(WeekDayType weekDay);
    List<R> findRestaurants(WeekDayType weekDay, LocalTime openTime, LocalTime closeTime);
    void removeRestaurant(Long id);


    E addTimeEntry(E schedule);
    E addTimeEntry(Long restaurantId,E schedule);
    List<E> addTimeEntries(Long restaurantId, List<E> scheduleList);
    void removeTimeEntry(Long id);

}
