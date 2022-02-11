package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.repositories.RestaurantRepository;
import com.baraabytes.restaurantsApp.api.repositories.RestaurantScheduleRepository;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService<Restaurant, Schedule> {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantScheduleRepository scheduleRepository;

    public RestaurantServiceImpl() {
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> findRestaurants(String name) {
        return restaurantRepository.findAllByNameStartsWith(name);
    }

    @Override
    public List<Restaurant> findRestaurants(WeekDayType weekDay) {
        List<Restaurant> restaurants = scheduleRepository.findAllByDayEquals(weekDay)
                .stream().map(schedule -> schedule.getRestaurant()).collect(Collectors.toList());
        return restaurants;
    }

    @Override
    public List<Restaurant> findRestaurants(WeekDayType weekDay,LocalDateTime openTime, LocalDateTime closeTime) {
        List<Restaurant> restaurants = scheduleRepository.findAllByDayEqualsAndOpenTimeGreaterThanEqualAndCloseTimeLessThan(
                weekDay,openTime,closeTime)
                .stream().map(schedule -> schedule.getRestaurant()).collect(Collectors.toList());
        return restaurants;
    }

    @Override
    public void removeRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Schedule addTimeEntry(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void removeTimeEntry(Long id) {
        scheduleRepository.deleteById(id);
    }
}
