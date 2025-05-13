package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;
import com.ti.estoque.enums.FatorMovimentacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.MovimentacaoEquipamento;

public interface MovimentacaoEquipamentoRepository extends JpaRepository<MovimentacaoEquipamento, Long>{
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Equipamento, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByEquipamentoId(Long id);
    Optional<MovimentacaoEquipamento> findByEquipamentoNumeroPatrimonio(String numeroPatrimonio);
    Optional<MovimentacaoEquipamento> findByEquipamentoTipoEquipamentoEquipamento(String tipoEquipamento);
    Optional<MovimentacaoEquipamento> findByEquipamentoMarcaMarca(String marca);
    Optional<MovimentacaoEquipamento> findByEquipamentoModeloModelo(String modelo);
    Optional<MovimentacaoEquipamento> findByEquipamentoServiceTagIgnoreCaseLike(String serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByTipoMovimentacaoId(Long id);
    Optional<MovimentacaoEquipamento> findByTipoMovimentacaoTipoMovimentacao(String tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByUsuarioId(Long id);
    Optional<MovimentacaoEquipamento> findByUsuarioNomeIgnoreCaseLike(String nome);

    //Busca dentro de uma Lista, e retorna uma lista
    List<MovimentacaoEquipamento> findByEquipamentoIdIn(List<Long> ids);
    List<MovimentacaoEquipamento> findByEquipamentoNumeroPatrimonioIgnoreCaseIn(List<String> numeroPatrimonio);
    List<MovimentacaoEquipamento> findByEquipamentoTipoEquipamentoEquipamentoIn(List<String> tipoEquipamento);
    List<MovimentacaoEquipamento> findByEquipamentoMarcaMarcaIn(List<String> marca);
    List<MovimentacaoEquipamento> findByEquipamentoModeloModeloIn(List<String> modelo);
    List<MovimentacaoEquipamento> findByEquipamentoServiceTagIgnoreCaseIn(List<String> serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByTipoMovimentacaoIdIn(List<Long> id);
    List<MovimentacaoEquipamento> findByTipoMovimentacaoTipoMovimentacaoIn(List<String> tipoMovimentacao);
        List<MovimentacaoEquipamento> findByTipoMovimentacaoFatorIn(List<FatorMovimentacao> fator);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByUsuarioIdIn(List<Long> id);
    List<MovimentacaoEquipamento> findByUsuarioNomeIgnoreCaseIn(List<String> nome);

}
