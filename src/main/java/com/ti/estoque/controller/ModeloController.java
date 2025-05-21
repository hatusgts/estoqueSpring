package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.service.ModeloService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ti.estoque.dto.ModeloRequestDTO;
import com.ti.estoque.dto.ModeloResponseDTO;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Modelos")
public class ModeloController {
    @Autowired
    private ModeloService modeloService;

    @PostMapping("/cadastro")
    public ResponseEntity<ModeloResponseDTO> cadastro(@RequestBody ModeloRequestDTO dto) {
        return ResponseEntity.ok(modeloService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ModeloResponseDTO> update(@RequestBody ModeloRequestDTO dto) {
        return ResponseEntity.ok(modeloService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModeloResponseDTO>> getAll(){
        return ResponseEntity.ok(modeloService.findAll());
    }
    
    @GetMapping("/find/id={id}")
    public ResponseEntity<ModeloResponseDTO> getID(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.findById(id));
    }

    @GetMapping("/find/id")
    public ResponseEntity<List<ModeloResponseDTO>> getID(@RequestParam(name="ids") List<Long> id) {
        return ResponseEntity.ok(modeloService.findByIds(id));
    }

    @GetMapping("/find/nome")
    public ResponseEntity<List<ModeloResponseDTO>> getByNome(@RequestParam(name="nomes")List<String> nomes) {
        return ResponseEntity.ok(modeloService.findByNomeModelo(nomes));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        modeloService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        modeloService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
