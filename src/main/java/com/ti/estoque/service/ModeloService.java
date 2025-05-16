package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Modelo;
import com.ti.estoque.repository.ModeloRepository;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    public List<Modelo> findAll(){
        List<Modelo> modelos = modeloRepository.findAll();
        
        if (modelos.isEmpty()) {
            throw new RuntimeException("Nenhum modelo encontrado");
        }
        
        return modelos;
    }

    public Modelo findId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado com id: " + id));
    }

    public List<Modelo> findByIds(List<Long> ids){
        List<Modelo> modelos = modeloRepository.findByIdIn(ids);

        if(modelos.isEmpty()) throw  new RuntimeException("Não foram encontrados modelos com estes id's.");

        return modelos;
    }

    public List<Modelo> findByNomeModelo(List<String> nomeModelo) {
        List<Modelo> modelos = modeloRepository.findByNomeModeloIgnoreCaseIn(nomeModelo);
        
        if (modelos.isEmpty()) {
            throw new RuntimeException("Nenhum modelo encontrado com os nomes fornecidos");
        }
        
        return modelos;
    }

    public Modelo saveModelo(Modelo item){
        return modeloRepository.save(item);
    }
}
