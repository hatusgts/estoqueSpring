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

import com.ti.estoque.dto.DepartamentoRequestDTO;
import com.ti.estoque.dto.DepartamentoResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.service.DepartamentoService;





@RestController
@RequestMapping("/Departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @PostMapping("/cadastro")
    public ResponseEntity<DepartamentoResponseDTO> createDP(@Validated(OnCreate.class)@RequestBody DepartamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.create(dto));
    }
    
    @PutMapping("/update")
    public ResponseEntity<DepartamentoResponseDTO> update(@Validated(OnUpdate.class) @RequestBody DepartamentoRequestDTO dto) {
        return ResponseEntity.ok(departamentoService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartamentoResponseDTO>> getAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }
    

    @GetMapping("/find/id={id}")
    public ResponseEntity<DepartamentoResponseDTO> dpGetByIds(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }
    
    @GetMapping("/find/ids")
    public ResponseEntity<List<DepartamentoResponseDTO>> findIds(@RequestParam(name="ids") List<Long> ids){
        List<DepartamentoResponseDTO> lista = departamentoService.findAllByIds(ids);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<DepartamentoResponseDTO>> findAll() {
        List<DepartamentoResponseDTO> departamentos = departamentoService.findAll();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/find/nomes")
    public ResponseEntity<List<DepartamentoResponseDTO>> findDps(@RequestParam(name="departamentos") List<String> dps) {
        List<DepartamentoResponseDTO> lista = departamentoService.findDepartamentos(dps);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name = "ids") List<Long> id) {
        departamentoService.deleteIds(id);
        return ResponseEntity.noContent().build();
    }
}
