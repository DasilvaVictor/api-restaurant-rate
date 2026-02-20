package com.chiris.app.restaurant_rate.service;

import java.util.List;

import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;

public interface IRestaurantService {
    List<RestaurantDTO> listRestaurants();
    RestaurantDetalleDTO getRestaurantById(Long id);
    RestaurantDetalleDTO createRestaurant(RestaurantDetalleDTO restaurantDetalleDTO);
}
