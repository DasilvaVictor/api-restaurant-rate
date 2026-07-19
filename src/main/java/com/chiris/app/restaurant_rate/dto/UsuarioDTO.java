package com.chiris.app.restaurant_rate.dto;

import com.chiris.app.restaurant_rate.model.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    // Solo entrada: nunca se serializa en las respuestas (evita filtrar el hash).
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Rol rol;
}
