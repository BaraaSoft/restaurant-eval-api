package com.baraabytes.restaurantsApp.api.services;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.repositories.RestaurantRepository;
import com.baraabytes.restaurantsApp.api.repositories.RestaurantScheduleRepository;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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
    public Iterable<Restaurant> allRestaurants(Integer pageNum,Integer pageSize) {
        Pageable page = PageRequest.of(pageNum,pageSize,Sort.by("name").ascending());

        return restaurantRepository.findAll(page);
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
    public List<Restaurant> findRestaurants(String name,Integer pageNum,Integer pageSize) {
        Pageable page = PageRequest.of(pageNum,pageSize);
        return restaurantRepository.findAllByNameStartsWith(name,page);
    }

    @Override
    public List<Restaurant> findRestaurants(WeekDayType weekDay) {
        List<Restaurant> restaurants = scheduleRepository.findAllByDayEquals(weekDay)
                .stream().map(schedule -> schedule.getRestaurant()).collect(Collectors.toList());
        return restaurants;
    }

    @Override
    public List<Restaurant> findRestaurants(WeekDayType weekDay, LocalTime openTime, LocalTime closeTime) {
        List<Restaurant> restaurants = scheduleRepository.findAllByDayEqualsAndOpenTimeLessThanEqualAndCloseTimeGreaterThanEqual(
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
    @Transactional
    public Schedule addTimeEntry(Long restaurantId, Schedule schedule) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
       if(restaurant.isPresent()){
          restaurant.get().addSchedules(schedule);
          restaurantRepository.save(restaurant.get());
          schedule.setRestaurant(restaurant.get());
          return scheduleRepository.save(schedule);
       }
        return null;
    }

    @Override
    @Transactional
    public List<Schedule> addTimeEntries(Long restaurantId, List<Schedule> scheduleList) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            restaurant.get().getSchedules().addAll(scheduleList);
            restaurantRepository.save(restaurant.get());
            scheduleList.stream()
                    .forEach(schedule -> schedule.setRestaurant(restaurant.get()));
            return scheduleRepository.saveAll(scheduleList);
        }
        return null;
    }




    @Override
    public void removeTimeEntry(Long id) {
        scheduleRepository.deleteById(id);
    }
}
