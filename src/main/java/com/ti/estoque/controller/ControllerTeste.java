package com.ti.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.estoque.dto.MovimentacaoEquipamentoRequestDTO;
import com.ti.estoque.service.MovimentacaoEquipamentoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/teste")
public class ControllerTeste {
    @Autowired
    private MovimentacaoEquipamentoService movimentacaoEquipamentoService;

        @PostMapping("/new")
    public ResponseEntity<?> createMovimentacao(@RequestBody MovimentacaoEquipamentoRequestDTO requestDTO) {
        System.out.println("Boddy: " + requestDTO);
        return ResponseEntity.ok(requestDTO);
    }
}
