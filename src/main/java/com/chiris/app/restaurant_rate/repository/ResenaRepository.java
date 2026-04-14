package com.chiris.app.restaurant_rate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiris.app.restaurant_rate.model.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByRestauranteId(Long restauranteId);
}
