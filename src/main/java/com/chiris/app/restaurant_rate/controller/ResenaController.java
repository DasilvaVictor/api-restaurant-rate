package com.chiris.app.restaurant_rate.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.service.IResenaService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/resenas")
@AllArgsConstructor
public class ResenaController {

    private final IResenaService resenaService;

    @PostMapping
    public ResponseEntity<ResenaDTO> postResena(@RequestBody ResenaDTO resena) {
        ResenaDTO newResena = resenaService.createResena(resena);
        return ResponseEntity.created(URI.create("/api/v1/resenas/" + newResena.getId())).body(newResena);
    }

}
