package com.ti.estoque.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ti.estoque.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ti.estoque.dto.UsuarioRequestDTO;
import com.ti.estoque.dto.UsuarioResponseDTO;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.enums.ModeloTrabalho;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> create(@Validated(OnCreate.class)@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioResponseDTO> update(@Validated(OnUpdate.class)@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/find-ids/")
    public ResponseEntity<List<UsuarioResponseDTO>> getByIds(@RequestParam(name="ids") List<Long> ids) {
        return ResponseEntity.ok(usuarioService.findByIds(ids));
    }

    @GetMapping("/find-nome/")
    public ResponseEntity<List<UsuarioResponseDTO>> getByNames(@RequestParam(name="nomes") List<String> nomes) {
        return ResponseEntity.ok(usuarioService.getNomes(nomes));
    }

    @GetMapping("/find-email/")
    public ResponseEntity<List<UsuarioResponseDTO>> getEmails(@RequestParam(name="emails") List<String> emails) {
        return ResponseEntity.ok(usuarioService.findByEmails(emails));
    }

    @GetMapping("/find-isAdmin={isAdmin}")
    public ResponseEntity<List<UsuarioResponseDTO>> getByisAdmins(@PathVariable boolean isAdmin) {
        return ResponseEntity.ok(usuarioService.isAdmins(isAdmin));
    }

    @GetMapping("/find-cargos/")
    public ResponseEntity<List<UsuarioResponseDTO>> getByCargos(@RequestParam(name="cargos") List<String> cargos) {
        return ResponseEntity.ok(usuarioService.findByCargos(cargos));
    }

    @GetMapping("/find-departamentos/")
    public ResponseEntity<List<UsuarioResponseDTO>> getByDepartamentos(@RequestParam(name="departamentos") List<String> dps) {
        return ResponseEntity.ok(usuarioService.findByDepartamentos(dps));
    }

    @GetMapping("/find-modelos-trabalho/")
    public ResponseEntity<List<UsuarioResponseDTO>> getByModelosTrabalho(@RequestParam(name = "modelos") List<String> modelos) {

        List<ModeloTrabalho> modelosEnum = modelos.stream()
            .map(valor -> {
                try {
                    return ModeloTrabalho.valueOf(valor.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Modelo de trabalho inválido: " + valor);
                }
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(usuarioService.findByModeloModeloTrabalho(modelosEnum));
    }

    @GetMapping("/find-escritorios")
    public ResponseEntity<List<UsuarioResponseDTO>> getEscritorios(@RequestParam(name="escritorios") List<String> escritorios) {
        return ResponseEntity.ok(usuarioService.findByEscritorio(escritorios));
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<Void> deleteId(@PathVariable Long id){
        usuarioService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-ids/")
    public ResponseEntity<Void> deleteIds(@RequestParam(name="ids") List<Long> ids){
        usuarioService.deleteIds(ids);
        return ResponseEntity.noContent().build();
    }
}
