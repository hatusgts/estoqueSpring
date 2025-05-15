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

    public List<Cargo> findAll(){
        return cargoRepository.findAll();
    }

    public List<Cargo> findById(List<Long> ids){
        return cargoRepository.findByIdIn(ids);
    }

    public List<Cargo> findByNomeCargoIgnoreCaseIn(List<String> nomeCargo){
        return cargoRepository.findByNomeCargoIgnoreCaseIn(nomeCargo);
    }
}
