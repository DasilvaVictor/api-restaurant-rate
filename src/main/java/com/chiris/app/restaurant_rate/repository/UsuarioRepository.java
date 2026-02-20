package com.chiris.app.restaurant_rate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiris.app.restaurant_rate.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}
