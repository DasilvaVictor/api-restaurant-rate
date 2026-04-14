package com.chiris.app.restaurant_rate.mapper;

import java.util.ArrayList;
import java.util.List;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDTO;
import com.chiris.app.restaurant_rate.dto.RestaurantDetalleDTO;
import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.model.Resena;
import com.chiris.app.restaurant_rate.model.Restaurant;
import com.chiris.app.restaurant_rate.model.Usuario;

public class Mapper {

    public static RestaurantDTO toDTO(Restaurant obj) {
        // Implement the mapping logic here
        return RestaurantDTO.builder()
                .id(obj.getId())
                .nombre(obj.getNombre())
                .tipoComida(obj.getTipoComida())
                .calificacionPromedio(obj.getResenas().stream()
                        .mapToDouble(resena -> resena.getCalificacion())
                        .average()
                        .orElse(0.0))
                .build();
    }

    public static RestaurantDetalleDTO toDetalleDTO(Restaurant obj) {
        if (obj == null) return null;
    
        List<Resena> resenasActules = (obj.getResenas() == null) ? new ArrayList<>() : obj.getResenas();
    
        Double promedio = resenasActules.stream()
                .mapToDouble(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    
        List<ResenaDTO> resenasDTOS = resenasActules.stream()
                .map(Mapper::toResenaDTO)
                .toList();
    
        return RestaurantDetalleDTO.builder()
                .id(obj.getId())
                .nombre(obj.getNombre())
                .tipoComida(obj.getTipoComida())
                .telefono(obj.getTelefono())
                .calificacionPromedio(promedio)
                .direccion(obj.getDireccion())
                .resenas(resenasDTOS)
                .build();
    }

    public static ResenaDTO toResenaDTO(Resena obj) {
        // Implement the mapping logic here
        return ResenaDTO.builder()
                .id(obj.getId())
                .comentario(obj.getComentario())
                .calificacion(obj.getCalificacion())
                .idRestaurant(obj.getRestaurante().getId())
                .idUsuario(obj.getUsuario().getId())
                .usuarioNombre(obj.getUsuario().getEmail())
                .build();
    }

    public static UsuarioDTO toUsuarioDTO(Usuario obj) {
        // Implement the mapping logic here
        return UsuarioDTO.builder()
                .id(obj.getId())
                .nombre(obj.getNombre())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .build();
    }
}
