package com.chiris.app.restaurant_rate.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.service.IUsuarioService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO newUser =usuarioService.createUser(usuario);
        return ResponseEntity.created(URI.create("/api/usuarios/" + newUser.getId())).body(newUser);
    }
}
