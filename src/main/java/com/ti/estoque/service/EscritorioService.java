package com.ti.estoque.service;

import java.util.ArrayList;
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

    public List<EscritorioResponseDTO> findByNome(List<String> nomes) {
        List<Escritorio> encontrados = new ArrayList<>();

        for (String nome : nomes) {
            List<Escritorio> resultados = escritorioRepository
                .findByNomeEscritorioContainingIgnoreCase(nome);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum escritório encontrado com nomes semelhantes aos fornecidos.");
        }

        return encontrados.stream()
                .distinct()
                .map(escritorioMapper::toDto)
                .collect(Collectors.toList());
    }

    public EscritorioResponseDTO create(EscritorioRequestDTO requestDTO){
        Escritorio escritorio = escritorioMapper.toEntity(requestDTO);
        Escritorio savedEscritorio = escritorioRepository.save(escritorio);
        return escritorioMapper.toDto(savedEscritorio);
    }

    public EscritorioResponseDTO update(EscritorioRequestDTO dto){
        Escritorio escritorio = escritorioRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Escritorio não encontrado"));

        escritorio.setNomeEscritorio(dto.getNomeEscritorio());

        Escritorio atualizado = escritorioRepository.save(escritorio);

        return escritorioMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Escritorio escritorio = escritorioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Escritorio não encontrado"));

        escritorioRepository.delete(escritorio);
    }

    public void deleteByIds(List<Long> ids) {
        List<Escritorio> escritorios = escritorioRepository.findAllById(ids);

        if (escritorios.isEmpty()) {
            throw new RuntimeException("Nenhum escritório encontrado com os IDs fornecidos.");
        }

        escritorioRepository.deleteAll(escritorios);
    }

}
