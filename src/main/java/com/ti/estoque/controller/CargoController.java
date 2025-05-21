package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.CargoRequestDTO;
import com.ti.estoque.dto.CargoResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.service.CargoService;




@RestController
@RequestMapping("/Cargos")
public class CargoController {

    @Autowired
    CargoService cargoService;

    //Post, criação de cargos
    @PostMapping("/cadastro")
    public ResponseEntity<CargoResponseDTO> createCargo(@Validated(OnCreate.class)@RequestBody CargoRequestDTO requestDTO) {
        CargoResponseDTO cargoResponse = cargoService.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cargoResponse);
    }

    //Put, Update de cargos
    @PutMapping("/update")
    public ResponseEntity<CargoResponseDTO> update(@Validated(OnUpdate.class) @RequestBody CargoRequestDTO dto) {
        return ResponseEntity.ok(cargoService.update(dto));
    }

    //Gets, Consultas de cargos
    @GetMapping("/all")
    public ResponseEntity<List<CargoResponseDTO>> findAll() {
        List<CargoResponseDTO> cargos = cargoService.findAll();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/find/id={id}")
    public ResponseEntity<CargoResponseDTO> getCargo(@PathVariable Long id) {
        CargoResponseDTO cargo = cargoService.findById(id);
        return ResponseEntity.ok(cargo);
    }

    @GetMapping("/find/ids")
    public ResponseEntity<List<CargoResponseDTO>> getIds(@RequestParam(name = "ids") List<Long> lista) {
        List<CargoResponseDTO> cargos = cargoService.findByIdIn(lista);
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/find/nomes")
    public ResponseEntity<List<CargoResponseDTO>> getByNome(@RequestParam(name = "cargos") List<String> nomes) {
        List<CargoResponseDTO> cargos = cargoService.findByNomeCargoIgnoreCaseContainingIn(nomes);
        return ResponseEntity.ok(cargos);
    }

    //delete
    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cargoService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> delete(@RequestParam(name = "ids") List<Long> id) {
        cargoService.deleteIds(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
