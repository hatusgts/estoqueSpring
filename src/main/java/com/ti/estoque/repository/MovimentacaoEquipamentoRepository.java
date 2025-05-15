package com.ti.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoEquipamento;

@Repository
public interface MovimentacaoEquipamentoRepository extends JpaRepository<MovimentacaoEquipamento, Long>{
    
    //Buscas individuais e retorna um objeto

    //Buscas pelas propriedades do objeto Equipamento, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByEquipamentoId(Long id);
    List<MovimentacaoEquipamento> findByEquipamentoNumeroPatrimonioIgnoreCaseLike(String numeroPatrimonio);
    List<MovimentacaoEquipamento> findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseLike(String tipoEquipamento);
    List<MovimentacaoEquipamento> findByEquipamentoMarcaNomeMarcaIgnoreCaseLike(String marca);
    List<MovimentacaoEquipamento> findByEquipamentoModeloNomeModeloIgnoreCaseLike(String modelo);
    List<MovimentacaoEquipamento> findByEquipamentoServiceTagIgnoreCaseLike(String serviceTag);

    //Buscas pelas propriedades do objeto TipoMovimentacao, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao);

    //Buscas pelas propriedades do objeto Usuario, em MovimentacaoEquipamento
    List<MovimentacaoEquipamento> findByUsuarioId(Long id);
    List<MovimentacaoEquipamento> findByUsuarioNomeIgnoreCaseLike(String nome);

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
