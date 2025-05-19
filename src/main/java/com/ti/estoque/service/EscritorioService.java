package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.EscritorioRequestDTO;
import com.ti.estoque.dto.EscritorioResponseDTO;
import com.ti.estoque.mappers.EscritorioMapper;
import com.ti.estoque.model.Escritorio;
import com.ti.estoque.repository.EscritorioRepository;

@Service
public class EscritorioService {
    @Autowired
    private EscritorioRepository escritorioRepository;
    
    @Autowired
    private EscritorioMapper escritorioMapper;

    public List<EscritorioResponseDTO> findAll(){
        List<Escritorio> escritorios = escritorioRepository.findAll();

        if(escritorios.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios");

        return escritorios.stream()
                .map(escritorioMapper::toDto)
                .collect(Collectors.toList());
    }

    public EscritorioResponseDTO findById(Long id){
        Escritorio escritorio = escritorioRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Escritorio com id: " + id + " Não encontrado"));
        
        return escritorioMapper.toDto(escritorio);
    }

    public List<EscritorioResponseDTO> findByIds(List<Long> ids){
        List<Escritorio> escritorios = escritorioRepository.findByIdIn(ids);

        if(escritorios.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios com esses ids");

        return escritorios.stream()
                .map(escritorioMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EscritorioResponseDTO> findByNome(List<String> escritorio){
        List<Escritorio> nomeEscritorio = escritorioRepository.findByNomeEscritorioIgnoreCaseIn(escritorio);

        if(nomeEscritorio.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios com esse nome");

        return nomeEscritorio.stream()
                .map(escritorioMapper::toDto)
                .collect(Collectors.toList());
    }

    public EscritorioResponseDTO saveEscritorio(EscritorioRequestDTO requestDTO){
        Escritorio escritorio = escritorioMapper.toEntity(requestDTO);
        Escritorio savedEscritorio = escritorioRepository.save(escritorio);
        return escritorioMapper.toDto(savedEscritorio);
    }
}
