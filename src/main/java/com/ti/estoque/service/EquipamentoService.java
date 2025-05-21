package com.ti.estoque.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.EquipamentoRequestDTO;
import com.ti.estoque.dto.EquipamentoResponseDTO;
import com.ti.estoque.mappers.EquipamentoMapper;
import com.ti.estoque.model.Equipamento;
import com.ti.estoque.repository.EquipamentoRepository;
import com.ti.estoque.repository.MarcaRepository;
import com.ti.estoque.repository.ModeloRepository;
import com.ti.estoque.repository.TipoEquipamentoRepository;

@Service
public class EquipamentoService {
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private TipoEquipamentoRepository tipoEquipamentoRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private EquipamentoMapper equipamentoMapper;
    @Autowired
    private MarcaRepository marcaRepository;

    public EquipamentoResponseDTO findById(Long id){
        Equipamento equipamento = equipamentoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Equipamento não encontrado"));

        return equipamentoMapper.toDto(equipamento);
    }

    public List<EquipamentoResponseDTO> findByIds(List<Long> ids) {
        List<Equipamento> equipamentos = equipamentoRepository.findAllById(ids);

        if (equipamentos.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com os IDs fornecidos.");
        }

        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<EquipamentoResponseDTO> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        if (equipamentos.isEmpty()) throw new RuntimeException("Nenhum equipamento encontrado.");
        return equipamentos.stream()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByPatrimonio(List<String> patrimonios) {
        List<Equipamento> encontrados = new ArrayList<>();

        for (String patrimonio : patrimonios) {
            List<Equipamento> resultados = equipamentoRepository
                .findByNumeroPatrimonioContainingIgnoreCase(patrimonio);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com os patrimônios semelhantes fornecidos.");
        }

        return encontrados.stream()
                .distinct()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByDescricao(List<String> descricoes) {
        List<Equipamento> encontrados = new ArrayList<>();

        for (String descricao : descricoes) {
            List<Equipamento> resultados = equipamentoRepository
                .findByTipoEquipamentoDescricaoContainingIgnoreCase(descricao);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com as descrições semelhantes fornecidas.");
        }

        return encontrados.stream()
                .distinct()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByMarca(List<String> marcas) {
        List<Equipamento> encontrados = new ArrayList<>();

        for (String marca : marcas) {
            List<Equipamento> resultados = equipamentoRepository
                .findByMarcaNomeMarcaContainingIgnoreCase(marca);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com marcas semelhantes às fornecidas.");
        }

        return encontrados.stream()
                .distinct()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByModelo(List<String> modelos) {
        List<Equipamento> encontrados = new ArrayList<>();

        for (String modelo : modelos) {
            List<Equipamento> resultados = equipamentoRepository
                .findByModeloNomeModeloContainingIgnoreCase(modelo);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com modelos semelhantes aos fornecidos.");
        }

        return encontrados.stream()
                .distinct()
                .map(equipamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipamentoResponseDTO> findByServiceTag(List<String> serviceTags) {
        List<Equipamento> encontrados = new ArrayList<>();

        for (String tag : serviceTags) {
            List<Equipamento> resultados = equipamentoRepository
                .findByServiceTagContainingIgnoreCase(tag);
            encontrados.addAll(resultados);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com service tags semelhantes às fornecidas.");
        }

        return encontrados.stream()
                .distinct()
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

    public EquipamentoResponseDTO create(EquipamentoRequestDTO dto) {
        Equipamento equipamento = equipamentoMapper.toEntity(dto);
        Equipamento savedEquipamento = equipamentoRepository.save(equipamento);
        return equipamentoMapper.toDto(savedEquipamento);
    }

    public EquipamentoResponseDTO update(EquipamentoRequestDTO dto){
        Equipamento equipamento = equipamentoRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamento.setNumeroPatrimonio(dto.getNumeroPatrimonio());
        equipamento.setTipoEquipamento(
            tipoEquipamentoRepository.findById(dto.getTipoEquipamentoID())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"))
        );
        equipamento.setMarca(
            marcaRepository.findById(dto.getIdMarca())
            .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"))
        );
        equipamento.setModelo(
            modeloRepository.findById(dto.getIdModelo())
            .orElseThrow(() -> new RuntimeException("Modelo não encontrado"))
        );
        equipamento.setServiceTag(dto.getServiceTag());
        equipamento.setDataCadastro(dto.getData());

        Equipamento atualizado = equipamentoRepository.save(equipamento);
        return equipamentoMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Equipamento equipamento = equipamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamentoRepository.delete(equipamento);
    }

    public void deleteByIds(List<Long> ids) {
        List<Equipamento> equipamentos = equipamentoRepository.findAllById(ids);

        if (equipamentos.isEmpty()) {
            throw new RuntimeException("Nenhum equipamento encontrado com os IDs fornecidos.");
        }

        equipamentoRepository.deleteAll(equipamentos);
    }


}
