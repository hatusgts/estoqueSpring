package com.ti.estoque.service;

import java.util.ArrayList;
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

    public MarcaResponseDTO findById(Long id){
        MarcaResponseDTO marca = marcaMapper.toDto(
            marcaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Marca não encontrado")));

        return marca;
    }

    public List<MarcaResponseDTO> findAll(){
        List<Marca> marcas = marcaRepository.findAll();

        if(marcas.isEmpty()){
            throw new RuntimeException("Não há Marcas Cadastradas");
        }

        return marcas.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MarcaResponseDTO> findByIdIn(List<Long> ids) {
        List<Marca> marcas = marcaRepository.findAllById(ids);

        if (marcas.isEmpty()) {
            throw new RuntimeException("Não foram encontradas marcas com os IDs fornecidos.");
        }

        return marcas.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MarcaResponseDTO> findByNomeMarca(List<String> nomes) {
        List<Marca> marcasEncontradas = new ArrayList<>();

        for (String nome : nomes) {
            List<Marca> resultados = marcaRepository.findByNomeMarcaContainingIgnoreCase(nome);
            marcasEncontradas.addAll(resultados);
        }

        if (marcasEncontradas.isEmpty()) {
            throw new RuntimeException("Nenhuma marca semelhante encontrada.");
        }

        return marcasEncontradas.stream()
                .distinct()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public MarcaResponseDTO create(MarcaRequestDTO requestDTO){
        Marca marca = marcaMapper.toEntity(requestDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDto(savedMarca);
    }

    public MarcaResponseDTO update(MarcaRequestDTO dto){
        Marca marca = marcaRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        marca.setNomeMarca(dto.getNomeMarca());

        Marca atualizada = marcaRepository.save(marca);

        return marcaMapper.toDto(atualizada);
    }

    public void deleteId(Long id){
        Marca marca = marcaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Marca não encontrado"));

        marcaRepository.delete(marca);
    }

    public void deleteIds(List<Long> ids) {
        List<Marca> marcas = marcaRepository.findAllById(ids);

        if (marcas.isEmpty()) {
            throw new RuntimeException("Nenhuma marca encontrada com os IDs fornecidos.");
        }

        marcaRepository.deleteAll(marcas);
    }

}