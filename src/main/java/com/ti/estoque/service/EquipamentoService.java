package com.ti.estoque.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ti.estoque.dto.EquipamentoRequestDTO;
import com.ti.estoque.dto.EquipamentoResponseDTO;
import com.ti.estoque.mappers.EquipamentoMapper;
import com.ti.estoque.model.Equipamento;
import com.ti.estoque.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    private final EquipamentoMapper equipamentoMapper;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, EquipamentoMapper equipamentoMapper){
        this.equipamentoRepository = equipamentoRepository;
        this.equipamentoMapper = equipamentoMapper;
    }

    public List<EquipamentoResponseDTO> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByPatrimonio(List<String> patrimonio) {
        List<Equipamento> equipamentos = equipamentoRepository.findByNumeroPatrimonioIgnoreCaseIn(patrimonio);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com os patrimônios fornecidos.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByDescricao(List<String> descricao) {
        List<Equipamento> equipamentos = equipamentoRepository.findByTipoEquipamentoDescricaoIgnoreCaseIn(descricao);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as descrições fornecidas.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByMarca(List<String> marca) {
        List<Equipamento> equipamentos = equipamentoRepository.findByMarcaNomeMarcaIgnoreCaseIn(marca);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as marcas fornecidas.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByModelo(List<String> modelo) {
        List<Equipamento> equipamentos = equipamentoRepository.findByModeloNomeModeloIgnoreCaseIn(modelo);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com os modelos fornecidos.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByServiceTag(List<String> serTag) {
        List<Equipamento> equipamentos = equipamentoRepository.findByServiceTagIgnoreCaseIn(serTag);
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado com as service tags fornecidas.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    // Datas

    public List<EquipamentoResponseDTO> findByData(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastro(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados em " + date + ".");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByDataBefore(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroBefore(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados antes de " + date + ".");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByDataAfter(LocalDate date) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroAfter(date);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados após " + date + ".");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByDataEntre(LocalDate inicio, LocalDate fim) {
        List<Equipamento> equipamentos = equipamentoRepository.findByDataCadastroBetween(inicio, fim);
        if (equipamentos.isEmpty()) throw new RuntimeException("Não foram encontrados equipamentos cadastrados entre " + inicio + " e " + fim + ".");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public EquipamentoResponseDTO saveEquipamento(EquipamentoRequestDTO dto) {
        Equipamento equipamento = equipamentoMapper.toEntity(dto);
        Equipamento savedEquipamento = equipamentoRepository.save(equipamento);
        return equipamentoMapper.toDto(savedEquipamento);
    }
}
