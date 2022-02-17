package com.baraabytes.restaurantsApp.api.repositories;

import com.baraabytes.restaurantsApp.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
