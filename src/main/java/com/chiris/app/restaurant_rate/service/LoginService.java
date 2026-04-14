package com.chiris.app.restaurant_rate.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.LoginDTO;
import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;
import com.chiris.app.restaurant_rate.utils.JwtUtil;

@Service
public class LoginService implements ILoginService {

private final UsuarioRepository usuarioRepo;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepo.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(usuario.getEmail(), usuario.getId());
    }

    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public UsuarioDTO createUser(UsuarioDTO usuario) {
        Usuario newUser = Usuario.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .build();
        Usuario usuarioSaved = usuarioRepo.save(newUser);
        return Mapper.toUsuarioDTO(usuarioSaved);
    }


}
