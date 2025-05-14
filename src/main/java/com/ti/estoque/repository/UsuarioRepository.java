package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //Pesquisas Por apenas um atributo
    Optional<Usuario> findByNomeIgnoreCaseLike(String nome);
    Optional<Usuario> findByCpfIgnoreCaseLike(String cpf);
    Optional<Usuario> findByEmailIgnoreCaseLike(String email);

    //Pesquisas que retornam listas

    List<Usuario> findByIsAdmin(boolean isAdmin);
    List<Usuario> findByCargoNomeCargo(String cargo);
    List<Usuario> findByDepartamentoNomeDepartamento(String departamento);
    List<Usuario> findByModeloTrabalho(ModeloTrabalho modelo);
    List<Usuario> findByEscritorioNomeEscritorio(String escritorio);
    List<Usuario> findByIsAtivo(boolean ativo);

    //Buscas por mais de um atributo, que retornam listas

    List<Usuario> findByCargoNomeCargo(List<String> cargo);
    List<Usuario> findByDepartamentoNomeDepartamento(List<String> departamento);
    List<Usuario> findByModeloTrabalho(List <ModeloTrabalho> modelo);
    List<Usuario> findByEscritorioNomeEscritorio(List<String> escritorio);
}
