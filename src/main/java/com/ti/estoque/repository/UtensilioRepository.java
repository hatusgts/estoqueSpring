package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Utensilio;
;
public interface UtensilioRepository extends JpaRepository<Utensilio, Long>{
    
    //Pesquisas por apenas um atributo, que retornam apenas um objeto

    Optional<Utensilio> findByTipoEquipamentoId(long id);
    Optional<Utensilio> findByTipoEquipamentoDescricaoIgnoreCaseLike(String descricao);
    Optional<Utensilio> findByMarcaNomeMarcaIgnoreCaseLike(String nomeMarca);
    Optional<Utensilio> findByModeloNomeModeloIgnoreCaseLike(String nomeModelo);

    //Pesquisas por apenas um atributo, que retornam uma lista de objetos
    List<Utensilio> findByTipoEquipamentoIdIn(List<Long> ids);
    List<Utensilio> findByTipoEquipamentoDescricaoIgnoreCaseIn(List<String> descricao);
    List<Utensilio> findByMarcaNomeMarcaIgnoreCaseIn(List <String> nomeMarca);
    List<Utensilio> findByModeloNomeModeloIgnoreCaseIn(List <String> nomeModelo);
}
