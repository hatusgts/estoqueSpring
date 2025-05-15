package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoUtensilio;

@Repository
public interface MovimentacaoUtensilioRepository extends JpaRepository<MovimentacaoUtensilio, Long> {
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    List<MovimentacaoUtensilio> findByUtensilioId(Long id);
    List<MovimentacaoUtensilio> findByUtensilioTipoEquipamentoDescricaoIgnoreCaseLike(String tipoEquipamento);
    List<MovimentacaoUtensilio> findByUtensilioMarcaNomeMarcaIgnoreCaseLike(String marca);
    List<MovimentacaoUtensilio> findByUtensilioModeloNomeModeloIgnoreCaseLike(String modelo);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoUtensilio> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    List<MovimentacaoUtensilio> findByUsuarioId(Long id);
    List<MovimentacaoUtensilio> findByUsuarioNomeIgnoreCaseLike(String nome);

    //Busca dentro de uma Lista, e retorna uma lista

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    List<MovimentacaoUtensilio> findByUtensilioIdIn(List<Long> ids);
    List<MovimentacaoUtensilio> findByUtensilioTipoEquipamentoDescricaoIgnoreCaseIn(List<String> tipoEquipamento);
    List<MovimentacaoUtensilio> findByUtensilioMarcaNomeMarcaIgnoreCaseIn(List<String> marca);
    List<MovimentacaoUtensilio> findByUtensilioModeloNomeModeloIgnoreCaseIn(List<String> modelo);

    //Busca pelas propriedades de tipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoUtensilio> findByTipoMovimentacaoIn(List<TipoMovimentacao> tipoMovimentacao);
    

}
