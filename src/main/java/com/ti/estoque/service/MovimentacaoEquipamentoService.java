package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoEquipamento;
import com.ti.estoque.repository.MovimentacaoEquipamentoRepository;

@Service
public class MovimentacaoEquipamentoService {

    @Autowired
    private MovimentacaoEquipamentoRepository movimentacao;
    
    private <T> List<T> validarLista(List<T> lista, String mensagemErro) {
        if (lista.isEmpty()) {
            throw new RuntimeException(mensagemErro);
        }
        return lista;
    }

    public List<MovimentacaoEquipamento> findAll() {
        return validarLista(movimentacao.findAll(), "Não foram encontradas movimentações de equipamentos.");
    }

    public MovimentacaoEquipamento findId(Long id) {
        return movimentacao.findById(id)
            .orElseThrow(() -> new RuntimeException("Não foi encontrado uma movimentação com esse id."));
    }

    public List<MovimentacaoEquipamento> findByNumPatrimonio(String numPatrimonio) {
        return validarLista(
            movimentacao.findByEquipamentoNumeroPatrimonioIgnoreCaseLike(numPatrimonio),
            "Não foram encontrados registros com esse número de Patrimônio."
        );
    }

    public List<MovimentacaoEquipamento> findByTipoEquipamento(String tipoEquipamento) {
        return validarLista(
            movimentacao.findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseLike(tipoEquipamento),
            "Não foram encontrados registros desse tipo de Equipamento."
        );
    }

    public List<MovimentacaoEquipamento> findByMarca(String marca) {
        return validarLista(
            movimentacao.findByEquipamentoMarcaNomeMarcaIgnoreCaseLike(marca),
            "Não foram encontrados registros de equipamentos dessa marca."
        );
    }

    public List<MovimentacaoEquipamento> findByModelo(String modelo) {
        return validarLista(
            movimentacao.findByEquipamentoModeloNomeModeloIgnoreCaseLike(modelo),
            "Não foram encontrados registros de equipamentos desse modelo."
        );
    }

    public List<MovimentacaoEquipamento> findByServiceTag(String serviceTag) {
        return validarLista(
            movimentacao.findByEquipamentoServiceTagIgnoreCaseLike(serviceTag),
            "Não foram encontrados registros de equipamentos com essa Service Tag."
        );
    }

    public List<MovimentacaoEquipamento> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        return validarLista(
            movimentacao.findByTipoMovimentacao(tipoMovimentacao),
            "Não foram encontrados registros desse tipo de Movimentação."
        );
    }

    public List<MovimentacaoEquipamento> findByUserId(Long id) {
        return validarLista(
            movimentacao.findByUsuarioId(id),
            "Não foram encontrados registros de equipamentos movimentados com esse ID."
        );
    }

    public List<MovimentacaoEquipamento> findByUserName(String name) {
        return validarLista(
            movimentacao.findByUsuarioNomeIgnoreCaseLike(name),
            "Não foram encontrados registros desse Usuário."
        );
    }

    public List<MovimentacaoEquipamento> getEquipamentoIds(List<Long> ids) {
        return validarLista(
            movimentacao.findByEquipamentoIdIn(ids),
            "Não foram encontrados registros de equipamentos com estes IDs."
        );
    }

    public List<MovimentacaoEquipamento> getNumPatrimonios(List<String> patrimonios) {
        return validarLista(
            movimentacao.findByEquipamentoNumeroPatrimonioIgnoreCaseIn(patrimonios),
            "Não foram encontrados registros de equipamentos com estes números de Patrimônio."
        );
    }

    public List<MovimentacaoEquipamento> getTiposEquipamentos(List<String> tipoEquipamento) {
        return validarLista(
            movimentacao.findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseIn(tipoEquipamento),
            "Não foram encontrados registros de equipamentos desses tipos."
        );
    }

    public List<MovimentacaoEquipamento> getMarcas(List<String> marcas) {
        return validarLista(
            movimentacao.findByEquipamentoMarcaNomeMarcaIgnoreCaseIn(marcas),
            "Não foram encontrados registros de equipamentos dessas marcas."
        );
    }

    public List<MovimentacaoEquipamento> getModelos(List<String> modelos) {
        return validarLista(
            movimentacao.findByEquipamentoModeloNomeModeloIgnoreCaseIn(modelos),
            "Não foram encontrados registros de equipamentos desses modelos."
        );
    }

    public List<MovimentacaoEquipamento> getServiceTags(List<String> serviceTags) {
        return validarLista(
            movimentacao.findByEquipamentoServiceTagIgnoreCaseIn(serviceTags),
            "Não foram encontrados registros de equipamentos com essas Service Tags."
        );
    }

    public List<MovimentacaoEquipamento> getTiposMovimentacao(List<TipoMovimentacao> tipos) {
        return validarLista(
            movimentacao.findByTipoMovimentacaoIn(tipos),
            "Não foram encontrados registros desses tipos de Movimentação."
        );
    }

    public List<MovimentacaoEquipamento> getUserIds(List<Long> ids) {
        return validarLista(
            movimentacao.findByUsuarioIdIn(ids),
            "Não foram encontrados registros com esses IDs de Usuário."
        );
    }

    public List<MovimentacaoEquipamento> getUsers(List<String> nomes) {
        return validarLista(
            movimentacao.findByUsuarioNomeIgnoreCaseIn(nomes),
            "Não foram encontrados registros de equipamentos desses usuários."
        );
    }

    public MovimentacaoEquipamento saveMovimentacao(MovimentacaoEquipamento registro) {
        return movimentacao.save(registro);
    }
}
