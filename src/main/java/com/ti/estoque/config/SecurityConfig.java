package com.ti.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔁 ALTERE AQUI PARA ATIVAR/DESATIVAR A AUTENTICAÇÃO
    private static final boolean AUTENTICACAO_ATIVADA = false;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        if (AUTENTICACAO_ATIVADA) {
            http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/login").permitAll() // login liberado
                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // autenticação básica
        } else {
            http
                .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll() // tudo liberado
                );
        }

        return http.build();
    }
}