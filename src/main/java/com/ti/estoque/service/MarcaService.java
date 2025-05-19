package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.MarcaRequestDTO;
import com.ti.estoque.dto.MarcaResponseDTO;
import com.ti.estoque.mappers.MarcaMapper;
import com.ti.estoque.model.Marca;
import com.ti.estoque.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Autowired
    private MarcaMapper marcaMapper;

    public List<MarcaResponseDTO> findAll(){
        List<Marca> marcas = marcaRepository.findAll();

        if(marcas.isEmpty()){
            throw new RuntimeException("Não há Marcas Cadastradas");
        }

        return marcas.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MarcaResponseDTO> findByIdIn(List<Long> id){
        List<Marca> marcas = marcaRepository.findByIdIn(id);

        if(marcas.isEmpty()) throw new RuntimeException("Não foram encontradas Marcas nesses ID's");

        return marcas.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MarcaResponseDTO> findByNomeMarca(List<String> marca){
        List<Marca> marcas = marcaRepository.findByNomeMarcaIgnoreCaseIn(marca);

        if(marcas.isEmpty()) {
            throw new RuntimeException("Marcas não encontradas");
        }

        return marcas.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public MarcaResponseDTO saveMarca(MarcaRequestDTO requestDTO){
        Marca marca = marcaMapper.toEntity(requestDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDto(savedMarca);
    }
}