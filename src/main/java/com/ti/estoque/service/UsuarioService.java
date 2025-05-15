package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Consultas
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario userID(Long id){
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
    //nome
    public List<Usuario> nameStarting(String nome){
        return usuarioRepository.findByNomeIgnoreCaseStartingWith(nome);
    }

    public List<Usuario> nameIn(String nome){
        return usuarioRepository.findByNomeIgnoreCaseLike(nome);
    }

    public List<Usuario> findByCPF(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmailIgnoreCaseLike(email);
    }

    public List<Usuario> isAdmins(boolean isAdmin){
        return usuarioRepository.findByIsAdmin(isAdmin);
    }

    public List<Usuario> findByCargos(List<String> cargo){
        return usuarioRepository.findByCargoNomeCargoIgnoreCaseIn(cargo);
    }

    public List<Usuario> findByDepartamentos(List<String> departamentos){
        return usuarioRepository.findByDepartamentoNomeDepartamentoIgnoreCaseIn(departamentos);
    }

    public List<Usuario> findByModeloModeloTrabalho(List<ModeloTrabalho> modelos){
        return usuarioRepository.findByModeloIn(modelos);
    }

    public List<Usuario> findByEscritorio(List<String> escritorio){
        return usuarioRepository.findByEscritorioNomeEscritorioIgnoreCaseIn(escritorio);
    }

    public Usuario saveUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
