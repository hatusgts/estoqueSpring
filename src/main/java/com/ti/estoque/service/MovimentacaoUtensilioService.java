package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.model.MovimentacaoUtensilio;
import com.ti.estoque.repository.MovimentacaoUtensilioRepository;

@Service
public class MovimentacaoUtensilioService {

    @Autowired
    private MovimentacaoUtensilioRepository movimentacaoUtensilioRepository;

    @SuppressWarnings("unused")
    private List<MovimentacaoUtensilio> findAll(){
        List<MovimentacaoUtensilio> registros = movimentacaoUtensilioRepository.findAll();
            if (registros.isEmpty()) {
                throw new RuntimeException("Nenhum registro encontrado.");
            }
        return registros;
    }

    private <T> List<T> validarLista(List<T> lista, String mensagemErro) {
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagemErro);
        }
        return lista;
    }

    public List<MovimentacaoUtensilio> findByUtensilioId(Long id) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioId(id),
            "Não foram encontradas movimentações para o ID informado."
        );
    }

    public List<MovimentacaoUtensilio> findByTipoEquipamento(String tipoEquipamento) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioTipoEquipamentoDescricaoIgnoreCaseLike(tipoEquipamento),
            "Não foram encontrados utensílios desse tipo."
        );
    }

    public List<MovimentacaoUtensilio> findByMarca(String marca) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioMarcaNomeMarcaIgnoreCaseLike(marca),
            "Não foram encontrados utensílios com essa marca."
        );
    }

    public List<MovimentacaoUtensilio> findByModelo(String modelo) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioModeloNomeModeloIgnoreCaseLike(modelo),
            "Não foram encontrados utensílios com esse modelo."
        );
    }

    public List<MovimentacaoUtensilio> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        return validarLista(
            movimentacaoUtensilioRepository.findByTipoMovimentacao(tipoMovimentacao),
            "Não foram encontradas movimentações desse tipo."
        );
    }

    public List<MovimentacaoUtensilio> findByUsuarioId(Long id) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUsuarioId(id),
            "Não foram encontradas movimentações para este usuário."
        );
    }

    public List<MovimentacaoUtensilio> findByUsuarioNome(String nome) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUsuarioNomeIgnoreCaseLike(nome),
            "Não foram encontradas movimentações para o nome informado."
        );
    }

    public List<MovimentacaoUtensilio> findByUtensilioIds(List<Long> ids) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioIdIn(ids),
            "Não foram encontrados utensílios com os IDs fornecidos."
        );
    }

    public List<MovimentacaoUtensilio> findByTiposEquipamentos(List<String> tipos) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioTipoEquipamentoDescricaoIgnoreCaseIn(tipos),
            "Não foram encontrados utensílios com os tipos fornecidos."
        );
    }

    public List<MovimentacaoUtensilio> findByMarcas(List<String> marcas) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioMarcaNomeMarcaIgnoreCaseIn(marcas),
            "Não foram encontrados utensílios com as marcas fornecidas."
        );
    }

    public List<MovimentacaoUtensilio> findByModelos(List<String> modelos) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUtensilioModeloNomeModeloIgnoreCaseIn(modelos),
            "Não foram encontrados utensílios com os modelos fornecidos."
        );
    }

    public List<MovimentacaoUtensilio> findByTiposMovimentacao(List<TipoMovimentacao> tipos) {
        return validarLista(
            movimentacaoUtensilioRepository.findByTipoMovimentacaoIn(tipos),
            "Não foram encontradas movimentações dos tipos fornecidos."
        );
    }

    public List<MovimentacaoUtensilio> findByUsuarioIds(List<Long> ids) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUsuarioIdIn(ids),
            "Não foram encontradas movimentações para os IDs de usuário fornecidos."
        );
    }

    public List<MovimentacaoUtensilio> findByUsuarios(List<String> nomes) {
        return validarLista(
            movimentacaoUtensilioRepository.findByUsuarioNomeIgnoreCaseIn(nomes),
            "Não foram encontradas movimentações para os nomes de usuário fornecidos."
        );
    }

    public MovimentacaoUtensilio salvarMovimentacao(MovimentacaoUtensilio movimentacao) {
        return movimentacaoUtensilioRepository.save(movimentacao);
    }
}
