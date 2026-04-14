package com.chiris.app.restaurant_rate.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleUpdateDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Resena;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.repository.RestaurantRepository;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository resRepo;
    
    @Override
    public List<RestaurantDTO> listRestaurants() {
        return resRepo.getRestaurantsWithAvg();
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

    @Override
    public void deleteRestaurantById(Long id) {
        if (!resRepo.existsById(id)) {
            throw new RuntimeException("Restaurant no encontrado");
        }
        resRepo.deleteById(id);
    }

@Override
public RestaurantDetalleUpdateDTO updateRestaurant(Long id, RestaurantDetalleUpdateDTO dto) {

    Restaurant restaurant = resRepo.findById(id).orElseThrow();
    
        if (dto.getNombre() != null) {
            restaurant.setNombre(dto.getNombre());
        }
    
        if (dto.getDireccion() != null) {
            restaurant.setDireccion(dto.getDireccion());
        }
    
        if (dto.getTipoComida() != null) {
            restaurant.setTipoComida(dto.getTipoComida());
        }
    
        if (dto.getTelefono() != null) {
            restaurant.setTelefono(dto.getTelefono());
        }
    
        Restaurant saved = resRepo.save(restaurant);
        
        return new RestaurantDetalleUpdateDTO(
            saved.getNombre(),
            saved.getTipoComida(),
            saved.getDireccion(),
            saved.getTelefono()
        );
    }

}
