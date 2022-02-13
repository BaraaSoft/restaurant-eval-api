package com.baraabytes.restaurantsApp.api.entities;

import com.baraabytes.restaurantsApp.api.types.WeekDayType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id ;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime openTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime closeTime;

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

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
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
