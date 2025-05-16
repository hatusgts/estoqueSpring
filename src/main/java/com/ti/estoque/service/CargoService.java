package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Cargo;
import com.ti.estoque.repository.CargoRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<Cargo> findAll() {
        List<Cargo> cargos = cargoRepository.findAll();
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado.");
        }
        return cargos;
    }

    public List<Cargo> findById(List<Long> ids) {
        List<Cargo> cargos = cargoRepository.findByIdIn(ids);
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado com os IDs fornecidos.");
        }
        return cargos;
    }

    public List<Cargo> findByNomeCargoIgnoreCaseIn(List<String> nomeCargo) {
        List<Cargo> cargos = cargoRepository.findByNomeCargoIgnoreCaseIn(nomeCargo);
        if (cargos.isEmpty()) {
            throw new RuntimeException("Nenhum cargo encontrado com os nomes fornecidos.");
        }
        return cargos;
    }

    public Cargo saveCargo(Cargo item){
        return cargoRepository.save(item);
    }
}
