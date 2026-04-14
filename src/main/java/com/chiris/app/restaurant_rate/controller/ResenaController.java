package com.chiris.app.restaurant_rate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.ResenaRequestDTO;
import com.chiris.app.restaurant_rate.service.IResenaService;
import com.chiris.app.restaurant_rate.utils.JwtUtil;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/v1/resenas")
@AllArgsConstructor
public class ResenaController {

    private final IResenaService resenaService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ResenaDTO> postResena(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ResenaRequestDTO request
    ) {

        String token = authHeader.replace("Bearer ", "");

        Long userId = jwtUtil.extractUserId(token);

        ResenaDTO resena = new ResenaDTO();
        resena.setComentario(request.getComentario());
        resena.setCalificacion(request.getCalificacion());
        resena.setIdRestaurant(request.getIdRestaurant());
        resena.setIdUsuario(userId);
        
        ResenaDTO newResena = resenaService.createResena(resena);
        
        return ResponseEntity.ok(newResena);
    }
}
