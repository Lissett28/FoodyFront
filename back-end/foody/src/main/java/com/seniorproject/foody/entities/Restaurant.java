package com.seniorproject.foody.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Restaurant {

    private String id;

    private String name;

    private String address;

    private double distance;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant() {

    }
}
