package com.chiris.app.restaurant_rate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.ResenaRequestDTO;
import com.chiris.app.restaurant_rate.dto.ResenaUpdateRequestDTO;
import com.chiris.app.restaurant_rate.service.IResenaService;
import com.chiris.app.restaurant_rate.utils.JwtUtil;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        Long userId = extractUserId(authHeader);

        ResenaDTO resena = new ResenaDTO();
        resena.setComentario(request.getComentario());
        resena.setCalificacion(request.getCalificacion());
        resena.setIdRestaurant(request.getIdRestaurant());
        resena.setIdUsuario(userId);

        ResenaDTO newResena = resenaService.createResena(resena);

        return ResponseEntity.ok(newResena);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResenaDTO> updateResena(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id,
            @RequestBody ResenaUpdateRequestDTO request
    ) {

        Long userId = extractUserId(authHeader);

        ResenaDTO updated = resenaService.updateResena(id, userId, request);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id
    ) {

        Long userId = extractUserId(authHeader);

        resenaService.deleteResena(id, userId);

        return ResponseEntity.noContent().build();
    }

    private Long extractUserId(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtUtil.extractUserId(token);
    }
}
