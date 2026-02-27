package com.chiris.app.restaurant_rate.service;

import com.chiris.app.restaurant_rate.dto.LoginDTO;
import com.chiris.app.restaurant_rate.dto.UsuarioDTO;

public interface ILoginService {
    String login(LoginDTO login);
    UsuarioDTO createUser(UsuarioDTO usuario);
}
