package com.ti.estoque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.CargoRequestDTO;
import com.ti.estoque.dto.CargoResponseDTO;
import com.ti.estoque.mappers.CargoMapper;
import com.ti.estoque.model.Cargo;
import com.ti.estoque.repository.CargoRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public CargoResponseDTO findById(Long id) {
        Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
        
        return CargoMapper.toDto(cargo);
    }

    public List<CargoResponseDTO> findAll() {
        List<Cargo> cargos = cargoRepository.findAll();
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado.");
        }
        List<CargoResponseDTO> lista = new ArrayList<>();
        for(Cargo i : cargos){
            lista.add(CargoMapper.toDto(i));
        }
        return lista;
    }

    public List<CargoResponseDTO> findByIdIn(List<Long> ids) {
        List<Cargo> cargos = cargoRepository.findByIdIn(ids);
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado com os IDs fornecidos.");
        }
        List<CargoResponseDTO> lista = new ArrayList<>();
        for(Cargo i : cargos){
            lista.add(CargoMapper.toDto(i));
        }
        return lista;
    }

    public List<CargoResponseDTO> findByNomeCargoIgnoreCaseContainingIn(List<String> nomes) {
        List<Cargo> resultados = new ArrayList<>();

        for (String nome : nomes) {
            List<Cargo> encontrados = cargoRepository.findByNomeCargoIgnoreCaseContaining(nome);
            resultados.addAll(encontrados);
        }

        if (resultados.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado com os nomes fornecidos.");
        }

        List<Cargo> semDuplicados = resultados.stream()
            .distinct()
            .collect(Collectors.toList());

        List<CargoResponseDTO> lista = semDuplicados.stream()
            .map(CargoMapper::toDto)
            .collect(Collectors.toList());

        return lista;
    }

    public CargoResponseDTO create(CargoRequestDTO item){
        Cargo cargo = CargoMapper.toEntity(item);
        Cargo savedCargo = cargoRepository.save(cargo);
        return CargoMapper.toDto(savedCargo);
    }

    public CargoResponseDTO update(CargoRequestDTO item){
        Cargo cargo = cargoRepository.findById(item.getId())
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        cargo.setNomeCargo(item.getNomeCargo());

        Cargo atualizado = cargoRepository.save(cargo);

        return CargoMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Cargo cargo = cargoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        cargoRepository.delete(cargo);
    }

    public void deleteIds(List<Long> ids) {
        List<Cargo> cargos = cargoRepository.findAllById(ids);

        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado para os IDs fornecidos.");
        }

        cargoRepository.deleteAll(cargos);
    }
}
