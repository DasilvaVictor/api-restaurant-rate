package com.chiris.app.restaurant_rate.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleUpdateDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository resRepo;
    
    @Override
    public List<RestaurantDTO> listRestaurants() {
        return resRepo.getRestaurantsWithAvg();
    }

    @Override
    public List<RestaurantDTO> searchRestaurants(String query) {
        if (query == null || query.trim().isEmpty()) {
            return resRepo.getRestaurantsWithAvg();
        }
        return resRepo.searchRestaurantsWithAvg(query.trim());
    }

    @Override
    @Transactional(readOnly = true)
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

        return RestaurantDetalleUpdateDTO.builder()
                .nombre(saved.getNombre())
                .direccion(saved.getDireccion())
                .tipoComida(saved.getTipoComida())
                .telefono(saved.getTelefono())
                .build();
    }

}
