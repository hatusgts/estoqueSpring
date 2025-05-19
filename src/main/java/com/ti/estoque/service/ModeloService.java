package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.ModeloRequestDTO;
import com.ti.estoque.dto.ModeloResponseDTO;
import com.ti.estoque.mappers.ModeloMapper;
import com.ti.estoque.model.Modelo;
import com.ti.estoque.repository.ModeloRepository;

@Service 
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    public List<ModeloResponseDTO> findAll(){
        List<Modelo> modelos = modeloRepository.findAll();
        
        if (modelos.isEmpty()) {
            throw new RuntimeException("Nenhum modelo encontrado");
        }
        
        return modelos.stream()
                .map(ModeloMapper::toDto)
                .collect(Collectors.toList());
    }

    public ModeloResponseDTO findId(Long id) {
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado com id: " + id));
        
        return ModeloMapper.toDto(modelo);
    }

    public List<ModeloResponseDTO> findByIds(List<Long> ids){
        List<Modelo> modelos = modeloRepository.findByIdIn(ids);

        if(modelos.isEmpty()) throw new RuntimeException("Não foram encontrados modelos com estes id's.");

        return modelos.stream()
                .map(ModeloMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ModeloResponseDTO> findByNomeModelo(List<String> nomeModelo) {
        List<Modelo> modelos = modeloRepository.findByNomeModeloIgnoreCaseIn(nomeModelo);
        
        if (modelos.isEmpty()) {
            throw new RuntimeException("Nenhum modelo encontrado com os nomes fornecidos");
        }
        
        return modelos.stream()
                .map(ModeloMapper::toDto)
                .collect(Collectors.toList());
    }

    public ModeloResponseDTO saveModelo(ModeloRequestDTO requestDTO){
        Modelo modelo = ModeloMapper.toEntity(requestDTO);
        Modelo savedModelo = modeloRepository.save(modelo);
        return ModeloMapper.toDto(savedModelo);
    }
}