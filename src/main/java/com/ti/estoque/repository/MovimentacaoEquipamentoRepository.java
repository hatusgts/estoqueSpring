package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoEquipamento;

public interface MovimentacaoEquipamentoRepository extends JpaRepository<MovimentacaoEquipamento, Long>{
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Equipamento, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByEquipamentoId(Long id);
    Optional<MovimentacaoEquipamento> findByEquipamentoNumeroPatrimonioIgnoreCaseLike(String numeroPatrimonio);
    Optional<MovimentacaoEquipamento> findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseLike(String tipoEquipamento);
    Optional<MovimentacaoEquipamento> findByEquipamentoMarcaNomeMarcaIgnoreCaseLike(String marca);
    Optional<MovimentacaoEquipamento> findByEquipamentoModeloNomeModeloIgnoreCaseLike(String modelo);
    Optional<MovimentacaoEquipamento> findByEquipamentoServiceTagIgnoreCaseLike(String serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    Optional<MovimentacaoEquipamento> findByUsuarioId(Long id);
    Optional<MovimentacaoEquipamento> findByUsuarioNomeIgnoreCaseLike(String nome);

    //Busca dentro de uma Lista, e retorna uma lista

    //Busca Equipamento
    List<MovimentacaoEquipamento> findByEquipamentoIdIn(List<Long> ids);
    List<MovimentacaoEquipamento> findByEquipamentoNumeroPatrimonioIgnoreCaseIn(List<String> numeroPatrimonio);
    List<MovimentacaoEquipamento> findByEquipamentoTipoEquipamentoDescricaoIn(List<String> tipoEquipamento);
    List<MovimentacaoEquipamento> findByEquipamentoMarcaNomeMarcaIn(List<String> marca);
    List<MovimentacaoEquipamento> findByEquipamentoModeloNomeModeloIn(List<String> modelo);
    List<MovimentacaoEquipamento> findByEquipamentoServiceTagIgnoreCaseIn(List<String> serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByTipoMovimentacaoIn(List<TipoMovimentacao> tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByUsuarioIdIn(List<Long> id);
    List<MovimentacaoEquipamento> findByUsuarioNomeIgnoreCaseIn(List<String> nome);

}
