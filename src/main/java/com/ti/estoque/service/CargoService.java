package com.ti.estoque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.CargoRequestDTO;
import com.ti.estoque.mappers.CargoMapper;
import com.ti.estoque.model.Cargo;
import com.ti.estoque.repository.CargoRepository;
import com.ti.estoque.dto.CargoResponseDTO;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public CargoResponseDTO findDtoId(Long id){
        Cargo cargo = cargoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado com o ID: " + id));
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

    public List<CargoResponseDTO> findById(List<Long> ids) {
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

    public List<CargoResponseDTO> findByNomeCargoIgnoreCaseIn(List<String> nomeCargo) {
        List<Cargo> cargos = cargoRepository.findByNomeCargoIgnoreCaseIn(nomeCargo);
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado com os nomes fornecidos.");
        }
        List<CargoResponseDTO> lista = new ArrayList<>();
        for(Cargo i : cargos){
            lista.add(CargoMapper.toDto(i));
        }
        return lista;
    }


    public CargoResponseDTO saveCargo(CargoRequestDTO item){
        Cargo cargo = CargoMapper.toEntity(item);
        Cargo savedCargo = cargoRepository.save(cargo);
        return CargoMapper.toDto(savedCargo);
    }
}
