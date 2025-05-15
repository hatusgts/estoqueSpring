package com.ti.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Long>{
    
    //Pesquisas por apenas um atributo, que retornam apenas um objeto

    List<Utensilio> findByTipoEquipamentoId(long id);
    List<Utensilio> findByTipoEquipamentoDescricaoIgnoreCaseLike(String descricao);
    List<Utensilio> findByMarcaNomeMarcaIgnoreCaseLike(String nomeMarca);
    List<Utensilio> findByModeloNomeModeloIgnoreCaseLike(String nomeModelo);

    //Pesquisas por apenas um atributo, que retornam uma lista de objetos
    List<Utensilio> findByTipoEquipamentoIdIn(List<Long> ids);
    List<Utensilio> findByTipoEquipamentoDescricaoIgnoreCaseIn(List<String> descricao);
    List<Utensilio> findByMarcaNomeMarcaIgnoreCaseIn(List <String> nomeMarca);
    List<Utensilio> findByModeloNomeModeloIgnoreCaseIn(List <String> nomeModelo);
}
