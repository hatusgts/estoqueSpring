package com.ti.estoque.mappers;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ti.estoque.dto.UsuarioRequestDTO;
import com.ti.estoque.dto.UsuarioResponseDTO;
import com.ti.estoque.model.Cargo;
import com.ti.estoque.model.Departamento;
import com.ti.estoque.model.Escritorio;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.CargoRepository;
import com.ti.estoque.repository.DepartamentoRepository;
import com.ti.estoque.repository.EscritorioRepository;

@Component
public class UsuarioMapper {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final CargoRepository cargoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EscritorioRepository escritorioRepository;

    public UsuarioMapper(
        CargoRepository cargoRepository,
        DepartamentoRepository departamentoRepository,
        EscritorioRepository escritorioRepository
    ){
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
        this.escritorioRepository = escritorioRepository;
    }

    public Usuario toEntity(UsuarioRequestDTO dto){

        Usuario entity = new Usuario();

        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        Departamento departamento = departamentoRepository.findById(dto.getIdDepartamento())
        .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        Escritorio escritorio = escritorioRepository.findById(dto.getIdCargo())
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setAdmin(dto.isAdmin());
        entity.setSenha(encoder.encode(dto.getSenha()));
        entity.setCargo(cargo);
        entity.setDepartamento(departamento);
        entity.setModelo(dto.getModeloTrabalho());
        entity.setEscritorio(escritorio);

        if(dto.getDataCadastro() != null){
            entity.setDataCadastro(dto.getDataCadastro());
        }else{
            entity.setDataCadastro(LocalDate.now());
        }

        entity.setAtivo(dto.isAtivo());

        return entity;
    }

    public UsuarioResponseDTO toDto(Usuario entity){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setCargo(entity.getCargo().getNomeCargo());
        dto.setDepartamento(entity.getDepartamento().getNomeDepartamento());
        dto.setModeloTrabalho(entity.getModelo());
        dto.setEscritorio(entity.getEscritorio().getNomeEscritorio());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setAtivo(entity.isAtivo());

        return dto;
    }
}
