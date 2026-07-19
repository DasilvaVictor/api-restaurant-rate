package com.chiris.app.restaurant_rate.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.service.IUsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    // Listado de usuarios: solo ADMIN.
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listUsers());
    }

    // Obtener un usuario por id: solo ADMIN.
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getUser(id));
    }

    // Registro de usuarios: solo accesible por un ADMIN autenticado (ver SecurityConfig).
    @PostMapping
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO nuevo = usuarioService.createUser(usuario);
        return ResponseEntity.created(URI.create("/api/v1/usuarios/" + nuevo.getId())).body(nuevo);
    }

    // Edicion parcial de usuario: solo ADMIN.
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        UsuarioDTO actualizado = usuarioService.updateUser(id, usuario);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminacion de usuario: solo ADMIN.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
