package com.chiris.app.restaurant_rate.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDetalleDTO {
    private Long id;
    private String nombre;
    private String tipoComida;
    private Double calificacionPromedio;
    private String direccion;
    private String telefono;
    private List<ResenaDTO> resenas;
}
