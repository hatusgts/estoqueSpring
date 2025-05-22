package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.MovimentacaoUtensilioRequestDTO;
import com.ti.estoque.dto.MovimentacaoUtensilioResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.service.MovimentacaoUtensilioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Movimentacao-Utensilios")
public class MovimentacaoUtensilioController {

    @Autowired
    private MovimentacaoUtensilioService movimentacaoService;

    @PostMapping("/cadastro")
    public ResponseEntity<MovimentacaoUtensilioResponseDTO> create(@Validated(OnCreate.class)@RequestBody MovimentacaoUtensilioRequestDTO movimentacao){
        return ResponseEntity.ok(movimentacaoService.create(movimentacao));
    }

    @PutMapping("/update")
    public ResponseEntity<MovimentacaoUtensilioResponseDTO> update(@Validated(OnUpdate.class)@RequestBody MovimentacaoUtensilioRequestDTO dto) {        
        return ResponseEntity.ok(movimentacaoService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getAll() {
        return ResponseEntity.ok(movimentacaoService.findAll());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<MovimentacaoUtensilioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.findById(id));
    }

    @GetMapping("/find-ids/")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByIds(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(movimentacaoService.findByIds(ids));
    }

    @GetMapping("/find-utensiliosIds")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByUtensiliosIDs(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(movimentacaoService.getUtensilioIds(ids));
    }

    @GetMapping("/find-tipoEquipamentos")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByTipoEquipamentos(@RequestParam(name="tipos") List<String> tipos) {
        return ResponseEntity.ok(movimentacaoService.getByTiposEquipamentos(tipos));
    }

    @GetMapping("/find-marcas")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByMarcas(@RequestParam(name="marcas") List<String> marcas) {
        return ResponseEntity.ok(movimentacaoService.getByMarcas(marcas));
    }

    @GetMapping("/find-modelos")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByModelo(@RequestParam(name="modelos") List<String> modelo) {
        return ResponseEntity.ok(movimentacaoService.getByModelos(modelo));
    }

    @GetMapping("/find-tipoMovimentacao")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByTipo(@RequestParam(name="tipos") List<String> tipos) {
        return ResponseEntity.ok(movimentacaoService.getByTiposMovimentacaoFromStrings(tipos));
    }

    @GetMapping("/find-usuarios")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByUsers(@RequestParam(name="nomes") List<String> nomes) {
        return ResponseEntity.ok(movimentacaoService.getByUsuarios(nomes));
    }

    @GetMapping("/find-idUsuarios")
    public ResponseEntity<List<MovimentacaoUtensilioResponseDTO>> getByUsersId(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(movimentacaoService.getByUsuarioIds(ids));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        movimentacaoService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        movimentacaoService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
