package com.baraabytes.restaurantsApp.api.repositories;


import com.baraabytes.restaurantsApp.api.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant,Long> {
    Page<Restaurant> findAllByNameStartsWith(String name, Pageable pageable);
}
