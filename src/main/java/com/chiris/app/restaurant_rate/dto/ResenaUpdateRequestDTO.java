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
public class ResenaUpdateRequestDTO {
    private String comentario;
    private Double calificacion;
}
