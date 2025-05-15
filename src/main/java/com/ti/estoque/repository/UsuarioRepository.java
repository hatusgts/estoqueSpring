package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //Pesquisas Por apenas um atributo
    
    //Pesquisas que retornam listas
    List<Usuario> findByNomeIgnoreCaseLike(String nome);
    List<Usuario> findByNomeIgnoreCaseStartingWith(String comecoNome);
    List<Usuario> findByNomeIgnoreCaseIn(List<String> nome);

    List<Usuario> findByCpf(String cpf);
    List<Usuario> findByEmailIgnoreCaseLike(String email);
    List<Usuario> findByIsAdmin(boolean isAdmin);
    List<Usuario> findByIsAtivo(boolean ativo);

    //Buscas por mais de um atributo, que retornam listas

    List<Usuario> findByCargoNomeCargoIgnoreCaseIn(List<String> cargo);
    List<Usuario> findByDepartamentoNomeDepartamentoIgnoreCaseIn(List<String> departamento);
    List<Usuario> findByModeloIn(List <ModeloTrabalho> modelo);
    List<Usuario> findByEscritorioNomeEscritorioIgnoreCaseIn(List<String> escritorio);
}