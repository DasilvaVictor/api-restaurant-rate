package com.chiris.app.restaurant_rate.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Rol;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;
import com.chiris.app.restaurant_rate.utils.EmailYaRegistradoException;
import com.chiris.app.restaurant_rate.utils.UsuarioNoEncontradoException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> listUsers() {
        return usuarioRepo.findAll().stream()
                .map(Mapper::toUsuarioDTO)
                .toList();
    }

    @Override
    public UsuarioDTO getUser(Long id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return Mapper.toUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO createUser(UsuarioDTO usuario) {
        usuarioRepo.findByEmail(usuario.getEmail()).ifPresent(u -> {
            throw new EmailYaRegistradoException("El email ya está registrado");
        });

        // Rol por defecto USER; un ADMIN puede asignar ADMIN explicitamente.
        Rol rol = usuario.getRol() != null ? usuario.getRol() : Rol.USER;

        Usuario newUser = Usuario.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .rol(rol)
                .build();

        return Mapper.toUsuarioDTO(usuarioRepo.save(newUser));
    }

    @Override
    public UsuarioDTO updateUser(Long id, UsuarioDTO usuario) {
        Usuario existente = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        if (usuario.getNombre() != null) {
            existente.setNombre(usuario.getNombre());
        }

        // Cambio de email: validar que no lo tenga ya otro usuario.
        if (usuario.getEmail() != null && !usuario.getEmail().equals(existente.getEmail())) {
            usuarioRepo.findByEmail(usuario.getEmail()).ifPresent(u -> {
                throw new EmailYaRegistradoException("El email ya está registrado");
            });
            existente.setEmail(usuario.getEmail());
        }

        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        if (usuario.getRol() != null) {
            existente.setRol(usuario.getRol());
        }

        return Mapper.toUsuarioDTO(usuarioRepo.save(existente));
    }

    @Override
    public void deleteUser(Long id) {
        if (!usuarioRepo.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        usuarioRepo.deleteById(id);
    }
}
