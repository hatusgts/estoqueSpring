package com.ti.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.service.TipoEquipamentoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;

import com.ti.estoque.dto.TipoEquipamentoRequestDTO;
import com.ti.estoque.dto.TipoEquipamentoResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/Tipo-Equipamentos")
public class TipoEquipamentoController {

    @Autowired
    private TipoEquipamentoService tipoEquipamentoService;

    @PostMapping("/cadastro")
    public ResponseEntity<TipoEquipamentoResponseDTO> cadastro(@Validated(OnCreate.class) @RequestBody TipoEquipamentoRequestDTO dto) {
        return ResponseEntity.ok(tipoEquipamentoService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<TipoEquipamentoResponseDTO> update(@Validated(OnUpdate.class) @RequestBody TipoEquipamentoRequestDTO dto) {
        return ResponseEntity.ok(tipoEquipamentoService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoEquipamentoResponseDTO>> getAll() {
        return ResponseEntity.ok(tipoEquipamentoService.findAll());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<TipoEquipamentoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoEquipamentoService.findById(id));
    }

    @GetMapping("/find-ids/")
    public ResponseEntity<List<TipoEquipamentoResponseDTO>> getByIds(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(tipoEquipamentoService.getIds(ids));
    }

    @GetMapping("/find-descricao")
    public ResponseEntity<List<TipoEquipamentoResponseDTO>> getDescricao(@RequestParam(name="descicoes") List<String> descricoes) {
        return ResponseEntity.ok(tipoEquipamentoService.getDescricao(descricoes));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        tipoEquipamentoService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        tipoEquipamentoService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
