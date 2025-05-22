package com.ti.estoque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.TipoEquipamentoRequestDTO;
import com.ti.estoque.dto.TipoEquipamentoResponseDTO;
import com.ti.estoque.mappers.TipoEquipamentoMapper;
import com.ti.estoque.model.TipoEquipamento;
import com.ti.estoque.repository.TipoEquipamentoRepository;

@Service
public class TipoEquipamentoService {
    
    @Autowired 
    private TipoEquipamentoRepository tipoEquipamentoRepository;
    
    @Autowired
    private TipoEquipamentoMapper tipoEquipamentoMapper;

    public List<TipoEquipamentoResponseDTO> findAll(){
        List<TipoEquipamento> registros = tipoEquipamentoRepository.findAll();
        if (registros.isEmpty()) {
            throw new RuntimeException("Nenhum Tipo de Equipamento encontrado.");
        }
        return converterParaDTO(registros);
    }
    
    private List<TipoEquipamentoResponseDTO> converterParaDTO(List<TipoEquipamento> entidades) {
        return entidades.stream()
                .map(tipoEquipamentoMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public TipoEquipamentoResponseDTO findById(Long id){
        TipoEquipamento entidade = tipoEquipamentoRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Tipo não encontrado"));
        return tipoEquipamentoMapper.toDto(entidade);
    }

    public List<TipoEquipamentoResponseDTO> getIds(List<Long> ids){
        List<TipoEquipamento> tipos = tipoEquipamentoRepository.findByIdIn(ids);

        if(tipos.isEmpty()) throw new RuntimeException("Não foram encontrados Tipos de equipamentos cadastrados neste Id's");

        return converterParaDTO(tipos);
    }

    public List<TipoEquipamentoResponseDTO> findDescricao(String tipo){
        List<TipoEquipamento> tipos = tipoEquipamentoRepository.findByDescricaoIgnoreCaseLike(tipo);
        if (tipos.isEmpty()) {
            throw new RuntimeException("Nenhum Tipo encontrado semelhante a: " + tipo);
        }
        return converterParaDTO(tipos);
    }

    public List<TipoEquipamentoResponseDTO> getDescricao(List<String> termos) {
        List<TipoEquipamento> encontrados = new ArrayList<>();

        for (String termo : termos) {
            List<TipoEquipamento> resultados = tipoEquipamentoRepository
                .findByDescricaoIgnoreCaseLike("%" + termo + "%");

            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum Tipo encontrado semelhante aos termos: " + termos);
        }

        return converterParaDTO(encontrados);
    }

    public TipoEquipamentoResponseDTO create(TipoEquipamentoRequestDTO dto) {
        TipoEquipamento entidade = tipoEquipamentoMapper.toEntity(dto);
        TipoEquipamento salvo = tipoEquipamentoRepository.save(entidade);
        return tipoEquipamentoMapper.toDto(salvo);
    }
    
    public TipoEquipamentoResponseDTO update(TipoEquipamentoRequestDTO dto) {
        if (!tipoEquipamentoRepository.existsById(dto.getId())) {
            throw new RuntimeException("Tipo de Equipamento não encontrado com o ID: " + dto.getId());
        }
        
        TipoEquipamento tipo = tipoEquipamentoRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));

        tipo.setDescricao(dto.getDescricao());

        TipoEquipamento atualizado = tipoEquipamentoRepository.save(tipo);
        return tipoEquipamentoMapper.toDto(atualizado);
    }

    public void deleteId(Long id){
        TipoEquipamento tipo = tipoEquipamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tipo não encontrado"));

        tipoEquipamentoRepository.delete(tipo);
    }

    public void deleteIds(List<Long> ids) {
        List<TipoEquipamento> tipos = tipoEquipamentoRepository.findAllById(ids);

        if (tipos.isEmpty()) {
            throw new RuntimeException("Nenhum tipo encontrado com os IDs fornecidos: " + ids);
        }

        tipoEquipamentoRepository.deleteAll(tipos);
    }
}