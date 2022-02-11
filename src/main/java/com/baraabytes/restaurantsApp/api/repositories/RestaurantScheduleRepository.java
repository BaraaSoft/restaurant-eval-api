package com.baraabytes.restaurantsApp.api.repositories;

import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import com.baraabytes.restaurantsApp.api.entities.Schedule;
import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByDayEquals(WeekDayType weekDayType);
    List<Schedule> findAllByDayEqualsAndOpenTimeGreaterThanEqualAndCloseTimeLessThan(WeekDayType weekDayType, LocalTime openTime, LocalTime closeTime);
}
