package com.chiris.app.restaurant_rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chiris.app.restaurant_rate.dto.UsuarioDTO;
import com.chiris.app.restaurant_rate.mapper.Mapper;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
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
