package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.enums.FatorMovimentacao;
import com.ti.estoque.model.MovimentacaoEquipamento;
import com.ti.estoque.model.MovimentacaoUtensilio;

public interface MovimentacaoUtensilioRepository extends JpaRepository<MovimentacaoUtensilio, Long> {
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    Optional<MovimentacaoUtensilio> findByUtensilioId(Long id);
    Optional<MovimentacaoUtensilio> findByUtensilioTipoEquipamentoEquipamento(String tipoEquipamento);
    Optional<MovimentacaoUtensilio> findByUtensilioMarcaMarca(String marca);
    Optional<MovimentacaoUtensilio> findByUtensilioModeloModelo(String modelo);
    Optional<MovimentacaoUtensilio> findByUtensilioServiceTagIgnoreCaseLike(String serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    Optional<MovimentacaoUtensilio> findByTipoMovimentacaoId(Long id);
    Optional<MovimentacaoUtensilio> findByTipoMovimentacaoTipoMovimentacao(String tipoMovimentacao);
    Optional<MovimentacaoUtensilio> findByTipoMovimentacaoFator(FatorMovimentacao fator);
    
    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    Optional<MovimentacaoUtensilio> findByUsuarioId(Long id);
    Optional<MovimentacaoUtensilio> findByUsuarioNomeIgnoreCaseLike(String nome);

    //Busca dentro de uma Lista, e retorna uma lista

    //Buscas pelas propriedades do objeto Utensilio, em MovimentacaoUtensilio
    List<MovimentacaoUtensilio> findByUtensilioIdIn(List<Long> ids);
    List<MovimentacaoUtensilio> findByUtensilioTipoUtensilioUtensilioIn(List<String> tipoEquipamento);
    List<MovimentacaoUtensilio> findByUtensilioMarcaMarcaIn(List<String> marca);
    List<MovimentacaoUtensilio> findByUtensilioModeloModeloIn(List<String> modelo);

    //Busca pelas propriedades de tipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoUtensilio> findByTipoMovimentacaoIdIn(List<Long> ids);
    List<MovimentacaoUtensilio> findByTipoMovimentacaoTipoMovimentacaoIn(List<String> tipoMovimentacao);
    List<MovimentacaoUtensilio> findByTipoMovimentacaoFatorIn(List<String> fator);
    

}
