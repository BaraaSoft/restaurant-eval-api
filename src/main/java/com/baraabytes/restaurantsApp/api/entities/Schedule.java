package com.baraabytes.restaurantsApp.api.entities;

import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Schedule implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime closeTime;

    @Enumerated(EnumType.STRING)
    private WeekDayType day;

    @ManyToOne
    private Restaurant restaurant;

    public Schedule() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public WeekDayType getDay() {
        return day;
    }

    public void setDay(WeekDayType day) {
        this.day = day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
