package com.chiris.app.restaurant_rate.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.ResenaUpdateRequestDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Resena;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.ResenaRepository;
import com.chiris.app.restaurant_rate.repository.RestaurantRepository;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResenaService implements IResenaService {

    private final ResenaRepository resenaRepo;
    private final RestaurantRepository restRepo;
    private final UsuarioRepository usuarioRepo;

    @Override
    public ResenaDTO createResena(ResenaDTO resena) {

        Restaurant rest = restRepo.findById(resena.getIdRestaurant())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        Usuario usu = usuarioRepo.findById(resena.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Resena newResena = Resena.builder()
                .calificacion(resena.getCalificacion())
                .comentario(resena.getComentario())
                .restaurante(rest)
                .usuario(usu)
                .build();

        Resena saved = resenaRepo.save(newResena);

        return Mapper.toResenaDTO(saved);
    }

    @Override
    public ResenaDTO updateResena(Long id, Long userId, ResenaUpdateRequestDTO request) {

        Resena resena = resenaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));

        // Solo el autor de la reseña puede editarla.
        if (!resena.getUsuario().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar una reseña que no es tuya");
        }

        if (request.getComentario() != null) {
            resena.setComentario(request.getComentario());
        }

        if (request.getCalificacion() != null) {
            resena.setCalificacion(request.getCalificacion());
        }

        Resena saved = resenaRepo.save(resena);

        return Mapper.toResenaDTO(saved);
    }

    @Override
    public void deleteResena(Long id, Long userId) {

        Resena resena = resenaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada"));

        // Solo el autor de la reseña puede eliminarla.
        if (!resena.getUsuario().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes eliminar una reseña que no es tuya");
        }

        resenaRepo.delete(resena);
    }
}
