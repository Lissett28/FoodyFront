package com.seniorproject.foody.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {
    String id;

    String alias;

    String name;

    String image_url;

    Boolean is_closed;

    String url;

    long review_count;

    List<Category> Categories;

    double rating;

    List<Transaction> transactions;

    String price;

    Location location;

    String phone;

    double distance;

    public Business(String id, String alias, String name, String img_url, Boolean is_closed, String url, Long review_count, List<Category> categories, Double rating, List<Transaction> transactions, String price, Location location, String phone, Double distance) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.image_url = image_url;
        this.is_closed = is_closed;
        this.url = url;
        this.review_count = review_count;
        Categories = categories;
        this.rating = rating;
        this.transactions = transactions;
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.distance = distance;
    }

    public Business() {
    }
}
