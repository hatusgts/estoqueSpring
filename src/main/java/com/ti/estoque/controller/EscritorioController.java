package com.ti.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.EscritorioRequestDTO;
import com.ti.estoque.dto.EscritorioResponseDTO;
import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.service.EscritorioService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Escritorios")
public class EscritorioController {
    @Autowired
    private EscritorioService escritorioService;

    @PostMapping("/cadastro")
    public ResponseEntity<EscritorioResponseDTO> createEscritorio(@Validated(OnCreate.class)@RequestBody EscritorioRequestDTO requestDTO) {
        EscritorioResponseDTO escritorio = escritorioService.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(escritorio);
    }

    @PutMapping("/update")
    public ResponseEntity<EscritorioResponseDTO> updateEscritorio(@RequestBody EscritorioRequestDTO dto) {
        return ResponseEntity.ok(escritorioService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EscritorioResponseDTO>> getAll() {
        return ResponseEntity.ok(escritorioService.findAll());
    }
    

    @GetMapping("/find/{id}")
    public ResponseEntity<EscritorioResponseDTO> getId(@PathVariable Long id) {
        return ResponseEntity.ok(escritorioService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<EscritorioResponseDTO>> getIds(@RequestParam(name="ids") List<Long> ids){
        return ResponseEntity.ok(escritorioService.findByIds(ids));
    }

    @GetMapping("find/nomes")
    public ResponseEntity<List<EscritorioResponseDTO>> getNomes(@RequestParam(name="nomes") List<String> nomes){
        return ResponseEntity.ok(escritorioService.findByNome(nomes));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        escritorioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids")@PathVariable List<Long> ids){
        escritorioService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }
}