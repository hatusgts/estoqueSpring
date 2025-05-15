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

    public List<Equipamento> findAll(){
        return equipamentoRepository.findAll();
    }

    public List<Equipamento> findByPatrimonio(List<String> patrimonio){
        return equipamentoRepository.findByNumeroPatrimonioIgnoreCaseIn(patrimonio);
    }

    public List<Equipamento> findByDescricao(List<String> descricao){
        return equipamentoRepository.findByTipoEquipamentoDescricaoIgnoreCaseIn(descricao);
    }

    public List<Equipamento> findByMarca(List<String> marca){
        return equipamentoRepository.findByMarcaNomeMarcaIgnoreCaseIn(marca);
    }

    public List<Equipamento> findByModelo(List<String> modelo){
        return equipamentoRepository.findByModeloNomeModeloIgnoreCaseIn(modelo);
    }

    public List<Equipamento> findByServiceTag(List<String> serTag){
        return equipamentoRepository.findByServiceTagIgnoreCaseIn(serTag);
    }

    //Datas

    public List<Equipamento> findByData(LocalDate date){
        return equipamentoRepository.findByDataCadastro(date);
    }

    public List<Equipamento> findByDataBefore(LocalDate date){
        return equipamentoRepository.findByDataCadastroBefore(date);
    }

    public List<Equipamento> findByDataAfter(LocalDate date){
        return equipamentoRepository.findByDataCadastroAfter(date);
    }

    public List<Equipamento> findByDataEntre(LocalDate inicio, LocalDate fim){
        return equipamentoRepository.findByDataCadastroBetween(inicio, fim);
    }
}
