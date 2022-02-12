package com.baraabytes.restaurantsApp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class RestaurantsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsAppApplication.class, args);
    }

}
