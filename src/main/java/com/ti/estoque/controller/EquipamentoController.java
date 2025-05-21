package com.ti.estoque.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import com.ti.estoque.dto.EquipamentoRequestDTO;
import com.ti.estoque.dto.EquipamentoResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.service.EquipamentoService;





@RestController
@RequestMapping("/Equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping("/cadastro")
    public ResponseEntity<EquipamentoResponseDTO> createEquipamento(@Validated(OnCreate.class)@RequestBody EquipamentoRequestDTO requestDTO) {
        EquipamentoResponseDTO equipamento = equipamentoService.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(equipamento);
    }

    @PutMapping("/update")
    public ResponseEntity<EquipamentoResponseDTO> update(@Validated(OnUpdate.class) @RequestBody EquipamentoRequestDTO dto) {
        return ResponseEntity.ok(equipamentoService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipamentoResponseDTO>> getAll() {
        List<EquipamentoResponseDTO> lista = equipamentoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<EquipamentoResponseDTO> getId(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentoService.findById(id));
    }

    @GetMapping("/find/ids/")
    public ResponseEntity<List<EquipamentoResponseDTO>> getIds(@RequestParam(name="ids") List<Long> ids){
        return ResponseEntity.ok(equipamentoService.findByIds(ids));
    }

    @GetMapping("/find/patrimonio")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByNumPatrimonio(@RequestParam("numeroPat") List<String> numeroPatrimonio) {
        return ResponseEntity.ok(equipamentoService.findByPatrimonio(numeroPatrimonio));
    }

    @GetMapping("/find/descricao")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByDescricao(@RequestParam("descricao") List<String> descricao) {
        return ResponseEntity.ok(equipamentoService.findByDescricao(descricao));
    }

    @GetMapping("/find/marca")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByMarca(@RequestParam("marcas") List<String> marca) {
        return ResponseEntity.ok(equipamentoService.findByMarca(marca));
    }

    @GetMapping("/find/modelo")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByModelo(@RequestParam("modelos") List<String> modelos) {
        return ResponseEntity.ok(equipamentoService.findByModelo(modelos));
    }

    @GetMapping("/find/servicetag")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByServiceTag(@RequestParam("services") List<String> serviceTag) {
        return ResponseEntity.ok(equipamentoService.findByServiceTag(serviceTag));
    }

    @GetMapping("/find/{date}")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(equipamentoService.findByData(date));
    }

    @GetMapping("/find-before/{date}")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByDateBefore(@PathVariable LocalDate date) {
        return ResponseEntity.ok(equipamentoService.findByDataBefore(date));
    }

    @GetMapping("/find-after/{date}")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByDateAfter(@PathVariable LocalDate date) {
        return ResponseEntity.ok(equipamentoService.findByDataAfter(date));
    }

    @GetMapping("/find-between/intervalo")
    public ResponseEntity<List<EquipamentoResponseDTO>> getByDateBetween(
            @RequestParam("inicio") @DateTimeFormat(iso = ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = ISO.DATE) LocalDate fim) {

        return ResponseEntity.ok(equipamentoService.findByDataEntre(inicio, fim));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id) {
        equipamentoService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteByIds(@RequestParam(name = "ids") List<Long> id) {
        equipamentoService.deleteByIds(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
