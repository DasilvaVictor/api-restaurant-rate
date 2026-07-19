package com.chiris.app.restaurant_rate.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.LoginDTO;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;
import com.chiris.app.restaurant_rate.utils.JwtUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService implements ILoginService {

private final UsuarioRepository usuarioRepo;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepo.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(usuario.getEmail(), usuario.getId());
    }
}
