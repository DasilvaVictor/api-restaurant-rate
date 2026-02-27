package com.chiris.app.restaurant_rate.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.LoginDTO;
import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.service.ILoginService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class LoginController {

    private final ILoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
    
        String token = loginService.login(loginDTO);
    
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
    
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO newUser =loginService.createUser(usuario);
        return ResponseEntity.created(URI.create("/api/v1/usuarios/" + newUser.getId())).body(newUser);
    }
}
