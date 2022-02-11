package com.baraabytes.restaurantsApp.api.controllers;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.services.RestaurantService;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantsController {

    @Autowired
    RestaurantService<Restaurant, Schedule> restaurantService;

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> allRestaurants(){
        List<Restaurant> restaurants = restaurantService.allRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id){
        Restaurant restaurant = restaurantService.findRestaurant(id);
        if(restaurant == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(restaurant);
    }

    @GetMapping(value = "/{id}",params = {"day","openAt","closeAt"})
    public ResponseEntity<List<Restaurant>> allRestaurantsAvaiableOn(
            @PathVariable Long id,
            @RequestParam(value = "day") WeekDayType day,
            @RequestParam(value = "openAt") String openAtStr,
            @RequestParam(value = "closeAt") String closeAtStr)
    {

        List<Restaurant> restaurants = restaurantService
                .findRestaurants(day,LocalTime.parse(openAtStr),LocalTime.parse(closeAtStr));
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant){
        Restaurant restr = restaurantService.addRestaurant(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurant.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(restr);
    }



    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<Schedule>> getSchedule(@PathVariable Long id ){
        List<Schedule> timeEntry = restaurantService.findRestaurant(id).getSchedules();

        return  ResponseEntity.ok(timeEntry);
    }

    @PostMapping("/{id}/schedules")
    public ResponseEntity<Schedule> addToSchedule(@PathVariable Long id,@RequestBody Schedule schedule){
        Schedule timeEntry = restaurantService.addTimeEntry(schedule);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        params.put("scheduleId",schedule.getId().toString());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/schedules/{scheduleId}")
                .buildAndExpand(params)
                .toUri();
        return  ResponseEntity.created(uri).body(timeEntry);
    }

    @GetMapping("/{id}/schedules/{scheduleId}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id,@PathVariable Long scheduleId ){
        Schedule timeEntry = restaurantService.findRestaurant(id).getSchedules().stream()
                .filter(schedule -> schedule.getId() == scheduleId).findFirst().get();

        if(timeEntry == null)
            return ResponseEntity.notFound().build();

        return  ResponseEntity.ok(timeEntry);
    }








}
