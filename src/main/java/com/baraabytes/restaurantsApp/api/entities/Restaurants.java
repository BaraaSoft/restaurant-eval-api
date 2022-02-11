package com.baraabytes.restaurantsApp.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Restaurants implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    private String name;




}
