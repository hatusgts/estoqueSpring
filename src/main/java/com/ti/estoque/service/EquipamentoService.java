package com.ti.estoque.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Equipamento;
import com.ti.estoque.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<Equipamento> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado.");
        return equipamentos;
    }

    public List<Equipamento> findByPatrimonio(List<String> patrimonio) {
        List<Equipamento> equipamentos = equipamentoRepository.findByNumeroPatrimonioIgnoreCaseIn(patrimonio);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com os patrimônios fornecidos.");
        return equipamentos;
    }

    public List<Equipamento> findByDescricao(List<String> descricao) {
        List<Equipamento> equipamentos = equipamentoRepository.findByTipoEquipamentoDescricaoIgnoreCaseIn(descricao);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as descrições fornecidas.");
        return equipamentos;
    }

    public List<Equipamento> findByMarca(List<String> marca) {
        List<Equipamento> equipamentos = equipamentoRepository.findByMarcaNomeMarcaIgnoreCaseIn(marca);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as marcas fornecidas.");
        return equipamentos;
    }

    public List<Equipamento> findByModelo(List<String> modelo) {
        List<Equipamento> equipamentos = equipamentoRepository.findByModeloNomeModeloIgnoreCaseIn(modelo);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com os modelos fornecidos.");
        return equipamentos;
    }

    public List<Equipamento> findByServiceTag(List<String> serTag) {
        List<Equipamento> equipamentos = equipamentoRepository.findByServiceTagIgnoreCaseIn(serTag);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as service tags fornecidas.");
        return equipamentos;
    }

    // Datas

    public List<Equipamento> findByData(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastro(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados em " + date + ".");
        return equipamentos;
    }

    public List<Equipamento> findByDataBefore(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroBefore(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados antes de " + date + ".");
        return equipamentos;
    }

    public List<Equipamento> findByDataAfter(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroAfter(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados após " + date + ".");
        return equipamentos;
    }

    public List<Equipamento> findByDataEntre(LocalDate inicio, LocalDate fim) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroBetween(inicio, fim);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados entre " + inicio + " e " + fim + ".");
        return equipamentos;
    }

    public Equipamento saveEquipamento(Equipamento item){
        return equipamentoRepository.save(item);
    }
}
