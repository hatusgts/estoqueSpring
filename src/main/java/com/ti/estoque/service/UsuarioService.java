package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository pessoaRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Consultas
    public List<Usuario> findAll() {
        List<Usuario> usuarios = pessoaRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado.");
        }
        return usuarios;
    }

    public Usuario userID(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // Nome
    public List<Usuario> nameStarting(String nome) {
        List<Usuario> usuarios = pessoaRepository.findByNomeIgnoreCaseStartingWith(nome);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com nome iniciando com: " + nome);
        }
        return usuarios;
    }

    public List<Usuario> nameIn(String nome) {
        List<Usuario> usuarios = pessoaRepository.findByNomeIgnoreCaseLike(nome);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com nome semelhante a: " + nome);
        }
        return usuarios;
    }

    public List<Usuario> findByCPF(String cpf) {
        List<Usuario> usuarios = pessoaRepository.findByCpf(cpf);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com o CPF: " + cpf);
        }
        return usuarios;
    }

    public List<Usuario> findByEmail(String email) {
        List<Usuario> usuarios = pessoaRepository.findByEmailIgnoreCaseLike(email);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com email semelhante a: " + email);
        }
        return usuarios;
    }

    public List<Usuario> isAdmins(boolean isAdmin) {
        List<Usuario> usuarios = pessoaRepository.findByIsAdmin(isAdmin);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com status de administrador: " + isAdmin);
        }
        return usuarios;
    }

    public List<Usuario> findByCargos(List<String> cargo) {
        List<Usuario> usuarios = pessoaRepository.findByCargoNomeCargoIgnoreCaseIn(cargo);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os cargos fornecidos.");
        }
        return usuarios;
    }

    public List<Usuario> findByDepartamentos(List<String> departamentos) {
        List<Usuario> usuarios = pessoaRepository.findByDepartamentoNomeDepartamentoIgnoreCaseIn(departamentos);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os departamentos fornecidos.");
        }
        return usuarios;
    }

    public List<Usuario> findByModeloModeloTrabalho(List<ModeloTrabalho> modelos) {
        List<Usuario> usuarios = pessoaRepository.findByModeloIn(modelos);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os modelos de trabalho fornecidos.");
        }
        return usuarios;
    }

    public List<Usuario> findByEscritorio(List<String> escritorio) {
        List<Usuario> usuarios = pessoaRepository.findByEscritorioNomeEscritorioIgnoreCaseIn(escritorio);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os escritórios fornecidos.");
        }
        return usuarios;
    }

    public Usuario saveUser(Usuario item) {
        item.setSenha(encoder.encode(item.getSenha()));
        return pessoaRepository.save(item);
    }
}