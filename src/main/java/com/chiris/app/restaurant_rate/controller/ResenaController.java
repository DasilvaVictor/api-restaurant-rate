package com.chiris.app.restaurant_rate.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.service.IResenaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private IResenaService resenaService;

    @PostMapping
    public ResponseEntity<ResenaDTO> postResena(@RequestBody ResenaDTO resena) {
        ResenaDTO newResena = resenaService.createResena(resena);
        return ResponseEntity.created(URI.create("/api/resenas/" + newResena.getId())).body(newResena);
    }

}
