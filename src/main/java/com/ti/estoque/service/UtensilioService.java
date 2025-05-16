package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Utensilio;
import com.ti.estoque.repository.UtensilioRepository;

@Service
public class UtensilioService {

    @Autowired
    private UtensilioRepository utensilioRepository;

    public List<Utensilio> findByTipoEquipamentoId(long id) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoId(id);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse tipo de equipamento (ID).");
        return registros;
    }

    public List<Utensilio> findByTipoEquipamentoDescricao(String descricao) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoDescricaoIgnoreCaseLike(descricao);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse tipo de equipamento (descrição).");
        return registros;
    }

    public List<Utensilio> findByMarca(String nomeMarca) {
        List<Utensilio> registros = utensilioRepository.findByMarcaNomeMarcaIgnoreCaseLike(nomeMarca);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essa marca.");
        return registros;
    }

    public List<Utensilio> findByModelo(String nomeModelo) {
        List<Utensilio> registros = utensilioRepository.findByModeloNomeModeloIgnoreCaseLike(nomeModelo);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse modelo.");
        return registros;
    }

    // Métodos para múltiplos critérios (listas)
    public List<Utensilio> getTipoEquipamentoIds(List<Long> ids) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoIdIn(ids);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esses IDs de tipo de equipamento.");
        return registros;
    }

    public List<Utensilio> getTipoEquipamentoDescricoes(List<String> descricoes) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoDescricaoIgnoreCaseIn(descricoes);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essas descrições de tipo de equipamento.");
        return registros;
    }

    public List<Utensilio> getMarcas(List<String> marcas) {
        List<Utensilio> registros = utensilioRepository.findByMarcaNomeMarcaIgnoreCaseIn(marcas);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essas marcas.");
        return registros;
    }

    public List<Utensilio> getModelos(List<String> modelos) {
        List<Utensilio> registros = utensilioRepository.findByModeloNomeModeloIgnoreCaseIn(modelos);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esses modelos.");
        return registros;
    }

    public Utensilio saveUtensilio(Utensilio utensilio) {
        return utensilioRepository.save(utensilio);
    }
}