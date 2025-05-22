package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.service.UtensilioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ti.estoque.dto.UtensilioRequestDTO;
import com.ti.estoque.dto.UtensilioResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Utensilios")
public class UtensilioController {

    @Autowired
    private UtensilioService utensilioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UtensilioResponseDTO> cadastro(@Validated(OnCreate.class)@RequestBody UtensilioRequestDTO dto) {
        return ResponseEntity.ok(utensilioService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<UtensilioResponseDTO> update(@RequestBody UtensilioRequestDTO dto) {
        return ResponseEntity.ok(utensilioService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtensilioResponseDTO>> getAll() {
        return ResponseEntity.ok(utensilioService.findAll());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UtensilioResponseDTO> getId(@PathVariable Long id) {
        return ResponseEntity.ok(utensilioService.findById(id));
    }
    
    @GetMapping("/find-ids/")
    public ResponseEntity<List<UtensilioResponseDTO>> getId(@RequestParam List<Long> id) {
        return ResponseEntity.ok(utensilioService.getTipoEquipamentoIds(id));
    }

    @GetMapping("/find-tipo-equipamento-descricoes/")
    public ResponseEntity<List<UtensilioResponseDTO>> getTipoEquipamentoDescricoes(@RequestParam List<String> descricoes) {
        return ResponseEntity.ok(utensilioService.getTipoEquipamentoDescricoes(descricoes));
    }

    @GetMapping("/find-marcas/")
    public ResponseEntity<List<UtensilioResponseDTO>> getMarcas(@RequestParam List<String> marcas) {
        return ResponseEntity.ok(utensilioService.getMarcas(marcas));
    }

    @GetMapping("/find-modelos/")
    public ResponseEntity<List<UtensilioResponseDTO>> getModelos(@RequestParam List<String> modelos) {
        return ResponseEntity.ok(utensilioService.getModelos(modelos));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        utensilioService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        utensilioService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
