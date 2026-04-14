package com.chiris.app.restaurant_rate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDetalleUpdateDTO {
    private String nombre;
    private String direccion;
    private String tipoComida;
    private String telefono;
}
