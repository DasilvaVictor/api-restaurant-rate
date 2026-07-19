package com.chiris.app.restaurant_rate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("""
        SELECT new com.chiris.app.restaurant_rate.dto.RestaurantDTO(
            r.id,
            r.nombre,
            r.tipoComida,
            COALESCE(AVG(res.calificacion), 0.0)
        )
        FROM Restaurant r
        LEFT JOIN r.resenas res
        GROUP BY r.id, r.nombre, r.tipoComida
    """)
    List<RestaurantDTO> getRestaurantsWithAvg();

    @Query("""
        SELECT new com.chiris.app.restaurant_rate.dto.RestaurantDTO(
            r.id,
            r.nombre,
            r.tipoComida,
            COALESCE(AVG(res.calificacion), 0.0)
        )
        FROM Restaurant r
        LEFT JOIN r.resenas res
        WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(r.tipoComida) LIKE LOWER(CONCAT('%', :query, '%'))
        GROUP BY r.id, r.nombre, r.tipoComida
    """)
    List<RestaurantDTO> searchRestaurantsWithAvg(@Param("query") String query);
}
