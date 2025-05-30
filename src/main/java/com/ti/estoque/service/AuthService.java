package com.ti.estoque.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean login(String email, String rawPassword) {
        Optional<Usuario> optionalUser = usuarioRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isEmpty()) {
            return false;
        }

        Usuario user = optionalUser.get();
        return passwordEncoder.matches(rawPassword, user.getSenha());
    }
}
