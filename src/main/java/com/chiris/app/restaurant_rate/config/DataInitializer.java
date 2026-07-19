package com.chiris.app.restaurant_rate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chiris.app.restaurant_rate.model.Rol;
import com.chiris.app.restaurant_rate.model.Usuario;
import com.chiris.app.restaurant_rate.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    // Crea el usuario administrador por defecto si aun no existe (idempotente).
    // Los valores se pueden sobreescribir por variables de entorno en produccion.
    @Bean
    CommandLineRunner seedAdmin(
            UsuarioRepository usuarioRepo,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.nombre}") String nombre,
            @Value("${app.admin.email}") String email,
            @Value("${app.admin.password}") String password) {

        return args -> {
            if (usuarioRepo.findByEmail(email).isPresent()) {
                log.info("Usuario administrador '{}' ya existe; no se crea de nuevo.", email);
                return;
            }

            Usuario admin = Usuario.builder()
                    .nombre(nombre)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .rol(Rol.ADMIN)
                    .build();

            usuarioRepo.save(admin);
            log.info("Usuario administrador por defecto creado: {}", email);
        };
    }
}
