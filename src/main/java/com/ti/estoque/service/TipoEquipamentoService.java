package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.TipoEquipamento;
import com.ti.estoque.repository.TipoEquipamentoRepository;

@Service
public class TipoEquipamentoService {
    
    @Autowired 
    private TipoEquipamentoRepository tipoEquipamento;

    public List<TipoEquipamento> findAll(){
        List<TipoEquipamento> registros = tipoEquipamento.findAll();
            if (registros.isEmpty()) {
                throw new RuntimeException("Nenhum Tipo de Equipamento encontrado.");
            }
        return registros;
    }

    public List<TipoEquipamento> getIds(List<Long> ids){
        List<TipoEquipamento> tipos = tipoEquipamento.findByIdIn(ids);

        if(tipos.isEmpty()) throw new RuntimeException("Não foram encontrados Tipos de equipamentos cadastrados neste Id's");

        return tipos;
    }

    public TipoEquipamento findById(Long id){
        return tipoEquipamento.findById(id).orElseThrow(()-> new RuntimeException("Tipo não encontrado"));
    }

    public List<TipoEquipamento> findTipo(String tipo){
        List<TipoEquipamento> tipos = tipoEquipamento.findByDescricaoIgnoreCaseLike(tipo);
        if (tipos.isEmpty()) {
            throw new RuntimeException("Nenhum Tipo encontrado semelhante a: " + tipo);
        }
        return tipos;
    }

    public List<TipoEquipamento> getTipos(List<String> tipo){
        List<TipoEquipamento> tipos = tipoEquipamento.findByDescricaoIgnoreCaseIn(tipo);
        if (tipos.isEmpty()) {
            throw new RuntimeException("Nenhum Tipo encontrado semelhante a: " + tipo);
        }
        return tipos;
    }
}
