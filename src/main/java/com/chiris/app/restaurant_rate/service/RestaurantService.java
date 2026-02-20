package com.chiris.app.restaurant_rate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.repository.RestaurantRepository;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository resRepo;
    
    @Override
    public List<RestaurantDTO> listRestaurants() {
        return resRepo.findAll().stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public RestaurantDetalleDTO getRestaurantById(Long id) {
        return resRepo.findById(id)
                .map(Mapper::toDetalleDTO)
                .orElse(null);
    }

    @Override
    public RestaurantDetalleDTO createRestaurant(RestaurantDetalleDTO restaurantDetalleDTO) {
        Restaurant restaurant = Restaurant.builder()
                .nombre(restaurantDetalleDTO.getNombre())
                .tipoComida(restaurantDetalleDTO.getTipoComida())
                .direccion(restaurantDetalleDTO.getDireccion())
                .telefono(restaurantDetalleDTO.getTelefono())
                .build();
        resRepo.save(restaurant);
        return Mapper.toDetalleDTO(restaurant);
    }

}
