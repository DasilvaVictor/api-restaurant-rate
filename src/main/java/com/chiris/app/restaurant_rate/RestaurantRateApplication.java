package com.chiris.app.restaurant_rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

// Se excluye UserDetailsServiceAutoConfiguration: la autenticacion es 100% manual via JWT
// (JwtAuthenticationFilter + LoginService), no se usa AuthenticationManager/UserDetailsService.
// Sin esto, Spring Boot crea un usuario en memoria con password aleatoria y la imprime en consola
// en cada arranque (inofensivo aqui porque httpBasic/formLogin estan deshabilitados, pero es ruido
// confuso en logs de produccion).
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class RestaurantRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantRateApplication.class, args);
	}

}
