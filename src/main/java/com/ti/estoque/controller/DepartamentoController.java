package com.ti.estoque.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.DepartamentoRequestDTO;
import com.ti.estoque.dto.DepartamentoResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.service.DepartamentoService;





@RestController
@RequestMapping("/Departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @PostMapping("/dp")
    public ResponseEntity<DepartamentoResponseDTO> createDP(@Validated(OnCreate.class)@RequestBody DepartamentoRequestDTO dto) {
        DepartamentoResponseDTO departamento = departamentoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
    }
    
    @PutMapping("/dp")
    public ResponseEntity<DepartamentoResponseDTO> update(@Validated(OnUpdate.class) @RequestBody DepartamentoRequestDTO dto) {
        return ResponseEntity.ok(departamentoService.update(dto));
    }

    @GetMapping("/dp/{id}")
    public ResponseEntity<DepartamentoResponseDTO> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponseDTO>> findAll() {
        List<DepartamentoResponseDTO> departamentos = departamentoService.findAll();
        return ResponseEntity.ok(departamentos);
    }
    
}
