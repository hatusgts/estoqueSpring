package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.service.MarcaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ti.estoque.dto.MarcaRequestDTO;
import com.ti.estoque.dto.MarcaResponseDTO;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping("/cadastro")
    public ResponseEntity<MarcaResponseDTO> cadastroMarca(@Validated(OnCreate.class)@RequestBody MarcaRequestDTO dto){
        return ResponseEntity.ok(marcaService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<MarcaResponseDTO> updateMarca(@Validated(OnUpdate.class) @RequestBody MarcaRequestDTO marca) {        
        return ResponseEntity.ok(marcaService.update(marca));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MarcaResponseDTO>> getAll() {
        return ResponseEntity.ok(marcaService.findAll());
    }

    @GetMapping("/find/id={id}")
    public ResponseEntity<MarcaResponseDTO> getId(@PathVariable Long id){
        return ResponseEntity.ok(marcaService.findById(id));
    }
    
    @GetMapping("/find/ids")
    public ResponseEntity<List<MarcaResponseDTO>> getIds(@RequestParam(name="ids") List<Long> ids){
        return ResponseEntity.ok(marcaService.findByIdIn(ids));
    }

    @GetMapping("/find/marcas")
    public ResponseEntity<List<MarcaResponseDTO>> getByNome(@RequestParam(name="marcas") List<String> marcas) {
        return ResponseEntity.ok(marcaService.findByNomeMarca(marcas));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        marcaService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name = "ids") List<Long> id) {
        marcaService.deleteIds(id);
        return ResponseEntity.noContent().build();
    }
}
