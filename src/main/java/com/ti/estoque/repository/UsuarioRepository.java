package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

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
    Optional<Usuario> findByEmailIgnoreCase(String email);
    List<Usuario> findByEmailIgnoreCaseLike(String email);
    List<Usuario> findByIsAdmin(boolean isAdmin);
    List<Usuario> findAllByIsAdmin(boolean isAdmin);

    //Buscas por mais de um atributo, que retornam listas

    List<Usuario> findByCargoNomeCargoIgnoreCaseLike(String nomeCargo);
    List<Usuario> findByDepartamentoNomeDepartamentoIgnoreCaseLike(String nomeDepartamento);
    List<Usuario> findByModeloIn(List<ModeloTrabalho> modelos);
    List<Usuario> findByEscritorioNomeEscritorioIgnoreCaseLike(String escritorio);
}