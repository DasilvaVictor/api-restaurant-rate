package com.chiris.app.restaurant_rate.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleUpdateDTO;
import com.chiris.app.restaurant_rate.service.IRestaurantService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/v1/restaurantes")
@AllArgsConstructor
public class RestaurantController {

    private final IRestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getRestaurantes(
            @RequestParam(name = "query", required = false) String query
    ) {
        if (query != null && !query.trim().isEmpty()) {
            return ResponseEntity.ok(restaurantService.searchRestaurants(query));
        }
        return ResponseEntity.ok(restaurantService.listRestaurants());
    }

    @PostMapping
    public ResponseEntity<RestaurantDetalleDTO> postRestaurant(@RequestBody RestaurantDetalleDTO restaurant) {
        RestaurantDetalleDTO newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.created(URI.create("/api/v1/restaurantes/" + newRestaurant.getId())).body(newRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetalleDTO> getRestaurant(@PathVariable Long id) {
        RestaurantDetalleDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantDetalleUpdateDTO> updateRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantDetalleUpdateDTO dto
    ) {
        RestaurantDetalleUpdateDTO updated = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
    

}
