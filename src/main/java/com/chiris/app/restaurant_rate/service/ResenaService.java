package com.chiris.app.restaurant_rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Resena;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.ResenaRepository;
import com.chiris.app.restaurant_rate.repository.RestaurantRepository;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;

@Service
public class ResenaService implements IResenaService {

    @Autowired
    private ResenaRepository resenaRepo;

    @Autowired
    private RestaurantRepository restRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public ResenaDTO createResena(ResenaDTO resena) {
        Restaurant rest = restRepo.findById(resena.getIdRestaurant())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Usuario usu = usuarioRepo.findById(resena.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resena newResena = Resena.builder()
                .calificacion(resena.getCalificacion())
                .comentario(resena.getComentario())
                .restaurante(rest)
                .usuario(usu)
                .build();
        Resena resenaSaved =resenaRepo.save(newResena);
        return Mapper.toResenaDTO(resenaSaved);
    }

}
