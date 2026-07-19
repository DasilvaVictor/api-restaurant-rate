package com.chiris.app.restaurant_rate.service;

import java.util.List;

import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleUpdateDTO;

public interface IRestaurantService {
    List<RestaurantDTO> listRestaurants();
    List<RestaurantDTO> searchRestaurants(String query);
    RestaurantDetalleDTO getRestaurantById(Long id);
    RestaurantDetalleDTO createRestaurant(RestaurantDetalleDTO restaurantDetalleDTO);
    void deleteRestaurantById(Long id);
    RestaurantDetalleUpdateDTO updateRestaurant(Long id, RestaurantDetalleUpdateDTO dto);
}
