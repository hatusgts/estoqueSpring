package com.ti.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Todas as rotas exigem autenticação
            )
            .httpBasic() // Ou .formLogin(), ou outro tipo de autenticação
            .and()
            .csrf().disable(); // Desativa CSRF para APIs (use com cuidado)

        return http.build();
    }
}
