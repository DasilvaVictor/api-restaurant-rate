package com.chiris.app.restaurant_rate.service;

import java.util.List;

import com.chiris.app.restaurant_rate.dto.UsuarioDTO;

public interface IUsuarioService {
    List<UsuarioDTO> listUsers();
    UsuarioDTO getUser(Long id);
    UsuarioDTO createUser(UsuarioDTO usuario);
    UsuarioDTO updateUser(Long id, UsuarioDTO usuario);
    void deleteUser(Long id);
}
