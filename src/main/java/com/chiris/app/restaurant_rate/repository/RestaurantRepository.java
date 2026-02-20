package com.chiris.app.restaurant_rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiris.app.restaurant_rate.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
