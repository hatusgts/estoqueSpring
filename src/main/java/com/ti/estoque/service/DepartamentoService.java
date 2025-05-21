package com.ti.estoque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.DepartamentoRequestDTO;
import com.ti.estoque.dto.DepartamentoResponseDTO;
import com.ti.estoque.mappers.DepartamentoMapper;
import com.ti.estoque.model.Departamento;
import com.ti.estoque.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoResponseDTO findById(Long id){
        Departamento departamento = departamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Departamento não encontrado com o ID: " + id));

        return DepartamentoMapper.toDto(departamento);
    }

    public List<DepartamentoResponseDTO> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado.");
        }
        List<DepartamentoResponseDTO> lista = new ArrayList<>();
        for(Departamento i : departamentos){
            lista.add(DepartamentoMapper.toDto(i));
        }
        return lista;
    }

    public List<DepartamentoResponseDTO> findAllByIds(List<Long> ids) {
        List<Departamento> departamentos = departamentoRepository.findByIdIn(ids);
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado com os IDs fornecidos.");
        }
        List<DepartamentoResponseDTO> lista = new ArrayList<>();
        for(Departamento i : departamentos){
            lista.add(DepartamentoMapper.toDto(i));
        }
        return lista;
    }

    public List<DepartamentoResponseDTO> findDepartamentos(List<String> nomesParciais) {
        List<Departamento> encontrados = new ArrayList<>();

        for (String nome : nomesParciais) {
            List<Departamento> encontradosPorNome = departamentoRepository.findByNomeDepartamentoIgnoreCaseContaining(nome);
            encontrados.addAll(encontradosPorNome);
        }

        if (encontrados.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado com os nomes fornecidos.");
        }

        // Evita duplicatas caso mais de um nome parcial retorne o mesmo registro
        List<Departamento> distintos = encontrados.stream().distinct().toList();

        return distintos.stream()
                .map(DepartamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartamentoResponseDTO create(DepartamentoRequestDTO item){
        Departamento departamento = DepartamentoMapper.toEntity(item);
        Departamento savedDepartamento = departamentoRepository.save(departamento);
        return DepartamentoMapper.toDto(savedDepartamento);
    }

    public DepartamentoResponseDTO update(DepartamentoRequestDTO item){
        Departamento dp = departamentoRepository.findById(item.getId())
        .orElseThrow(()-> new RuntimeException("Departamento não encontrado"));

        dp.setNomeDepartamento(item.getNomeDepartamento());

        Departamento atualizado = departamentoRepository.save(dp);
        return DepartamentoMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Departamento dp = departamentoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Departamento Não encontrado"));

        departamentoRepository.delete(dp);
    }
    public void deleteIds(List<Long> ids) {
        List<Departamento> departamentos = departamentoRepository.findByIdIn(ids);

        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado para os IDs fornecidos.");
        }

        departamentoRepository.deleteAll(departamentos);
    }
}