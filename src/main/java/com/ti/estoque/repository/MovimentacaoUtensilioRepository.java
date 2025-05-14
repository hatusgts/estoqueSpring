package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoUtensilio;

public interface MovimentacaoUtensilioRepository extends JpaRepository<MovimentacaoUtensilio, Long> {
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    Optional<MovimentacaoUtensilio> findByUtensilioId(Long id);
    Optional<MovimentacaoUtensilio> findByUtensilioTipoEquipamentoDescricaoIgnoreCaseLike(String tipoEquipamento);
    Optional<MovimentacaoUtensilio> findByUtensilioMarcaNomeMarcaIgnoreCaseLike(String marca);
    Optional<MovimentacaoUtensilio> findByUtensilioModeloNomeModeloIgnoreCaseLike(String modelo);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    Optional<MovimentacaoUtensilio> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    Optional<MovimentacaoUtensilio> findByUsuarioId(Long id);
    Optional<MovimentacaoUtensilio> findByUsuarioNomeIgnoreCaseLike(String nome);

    //Busca dentro de uma Lista, e retorna uma lista

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    List<MovimentacaoUtensilio> findByUtensilioIdIn(List<Long> ids);
    List<MovimentacaoUtensilio> findByUtensilioTipoEquipamentoDescricaoIgnoreCaseIn(List<String> tipoEquipamento);
    List<MovimentacaoUtensilio> findByUtensilioMarcaNomeMarcaIgnoreCaseIn(List<String> marca);
    List<MovimentacaoUtensilio> findByUtensilioModeloNomeModeloIgnoreCaseIn(List<String> modelo);

    //Busca pelas propriedades de tipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoUtensilio> findByTipoMovimentacaoIn(List<TipoMovimentacao> tipoMovimentacao);
    

}
