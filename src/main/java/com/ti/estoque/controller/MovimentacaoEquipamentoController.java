package com.ti.estoque.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.MovimentacaoEquipamentoRequestDTO;
import com.ti.estoque.dto.MovimentacaoEquipamentoResponseDTO;
import com.ti.estoque.service.MovimentacaoEquipamentoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ti.estoque.enums.TipoMovimentacao;


@RestController
@RequestMapping("/Movimentacao-Equipamento")
public class MovimentacaoEquipamentoController {
    @Autowired
    private MovimentacaoEquipamentoService movimentacaoService;

    @PostMapping("/cadastro")
    public ResponseEntity<MovimentacaoEquipamentoResponseDTO> create(@Validated(OnCreate.class)@RequestBody MovimentacaoEquipamentoRequestDTO movimentacao){
        return ResponseEntity.ok(movimentacaoService.create(movimentacao));
    }

    @PutMapping("/update")
    public ResponseEntity<MovimentacaoEquipamentoResponseDTO> update(@Validated(OnUpdate.class)@RequestBody MovimentacaoEquipamentoRequestDTO dto) {        
        return ResponseEntity.ok(movimentacaoService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getAll() {
        return ResponseEntity.ok(movimentacaoService.findAll());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<MovimentacaoEquipamentoResponseDTO> findId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.findById(id));
    }

    @GetMapping("/find-ids/")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> findIds(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(movimentacaoService.findByIds(ids));
    }

    @GetMapping("/find/numero-patrimonio")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> findPatrimonio(@RequestParam(name="numeros") List<String> patrimonios) {
        return ResponseEntity.ok(movimentacaoService.getNumPatrimonios(patrimonios));
    }

    @GetMapping("/find/tipo-equipamentos")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getByTipos(@RequestParam(name="tipos") List<String> tipos) {
        return ResponseEntity.ok(movimentacaoService.getTiposEquipamentos(tipos));
    }

    @GetMapping("/find/marcas")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getByMarcas(@RequestParam(name="marcas") List<String> marcas){
        return ResponseEntity.ok(movimentacaoService.getMarcas(marcas));
    }

    @GetMapping("/find/modelos")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getByModelos(@RequestParam(name="modelos") List<String> modelos) {
        return ResponseEntity.ok(movimentacaoService.getModelos(modelos));
    }

    @GetMapping("/find/service-tag")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getByServiceTag(@RequestParam(name="services") List<String> services) {
        return ResponseEntity.ok(movimentacaoService.getServiceTags(services));
    }

    @GetMapping("/find/tipo-movimentacao")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getByMoviment(@RequestParam(name = "tipos") List<String> tiposStr) {

        List<TipoMovimentacao> tipos = tiposStr.stream()
            .map(String::toUpperCase)
            .map(TipoMovimentacao::valueOf)
            .collect(Collectors.toList());

        return ResponseEntity.ok(movimentacaoService.getTiposMovimentacao(tipos));
    }

    @GetMapping("/find/user-ids")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getUsersIds(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(movimentacaoService.getUserIds(ids));
    }
    
    @GetMapping("/find/user-names")
    public ResponseEntity<List<MovimentacaoEquipamentoResponseDTO>> getUsersNames(@RequestParam List<String> nomes) {
        return ResponseEntity.ok(movimentacaoService.getUsers(nomes));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        movimentacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        movimentacaoService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
