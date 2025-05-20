package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.CargoRequestDTO;
import com.ti.estoque.dto.CargoResponseDTO;
import com.ti.estoque.service.CargoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/Cargo")
public class CargoController {

    @Autowired
    CargoService cargoService;

    @PostMapping("/create")
    public ResponseEntity<CargoResponseDTO> createCargo(@Valid@RequestBody CargoRequestDTO requestDTO) {
        //TODO: process POST request
        CargoResponseDTO cargoResponse = cargoService.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cargoResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CargoResponseDTO>> postMethodName() {
        //TODO: process POST request
        List<CargoResponseDTO> cargos = cargoService.findAll();
        return ResponseEntity.ok(cargos);
    }
    
    
}
