package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.UsuarioRequestDTO;
import com.ti.estoque.dto.UsuarioResponseDTO;
import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.mappers.UsuarioMapper;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.CargoRepository;
import com.ti.estoque.repository.DepartamentoRepository;
import com.ti.estoque.repository.EscritorioRepository;
import com.ti.estoque.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final EscritorioRepository escritorioRepository;

    private final DepartamentoRepository departamentoRepository;

    private final CargoRepository cargoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @SuppressWarnings("unused")
    UsuarioService(CargoRepository cargoRepository, DepartamentoRepository departamentoRepository, EscritorioRepository escritorioRepository) {
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
        this.escritorioRepository = escritorioRepository;
    }

    // Método auxiliar para converter lista de entidades para lista de DTOs
    private List<UsuarioResponseDTO> converterParaDTO(List<Usuario> entidades) {
        return entidades.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    // Consultas
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado.");
        }
        return converterParaDTO(usuarios);
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return usuarioMapper.toDto(usuario);
    }

    // Nome
    public List<UsuarioResponseDTO> nameStarting(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNomeIgnoreCaseStartingWith(nome);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com nome iniciando com: " + nome);
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> nameIn(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNomeIgnoreCaseLike(nome);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com nome semelhante a: " + nome);
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByCPF(String cpf) {
        List<Usuario> usuarios = usuarioRepository.findByCpf(cpf);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com o CPF: " + cpf);
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByEmail(String email) {
        List<Usuario> usuarios = usuarioRepository.findByEmailIgnoreCaseLike(email);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com email semelhante a: " + email);
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> isAdmins(boolean isAdmin) {
        List<Usuario> usuarios = usuarioRepository.findByIsAdmin(isAdmin);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com status de administrador: " + isAdmin);
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByCargos(List<String> cargo) {
        List<Usuario> usuarios = usuarioRepository.findByCargoNomeCargoIgnoreCaseIn(cargo);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os cargos fornecidos.");
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByDepartamentos(List<String> departamentos) {
        List<Usuario> usuarios = usuarioRepository.findByDepartamentoNomeDepartamentoIgnoreCaseIn(departamentos);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os departamentos fornecidos.");
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByModeloModeloTrabalho(List<ModeloTrabalho> modelos) {
        List<Usuario> usuarios = usuarioRepository.findByModeloIn(modelos);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os modelos de trabalho fornecidos.");
        }
        return converterParaDTO(usuarios);
    }

    public List<UsuarioResponseDTO> findByEscritorio(List<String> escritorio) {
        List<Usuario> usuarios = usuarioRepository.findByEscritorioNomeEscritorioIgnoreCaseIn(escritorio);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com os escritórios fornecidos.");
        }
        return converterParaDTO(usuarios);
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Usuario entidade = usuarioMapper.toEntity(dto);
        // A senha já está sendo codificada no mapper, então removemos essa linha
        // entidade.setSenha(encoder.encode(entidade.getSenha()));
        Usuario salvo = usuarioRepository.save(entidade);
        return usuarioMapper.toDto(salvo);
    }
    
    public UsuarioResponseDTO update(UsuarioRequestDTO dto) {
        if (!usuarioRepository.existsById(dto.getId())) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + dto.getId());
        }
        
        Usuario user = usuarioRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        user.setNome(dto.getNome());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setAdmin(dto.isAdmin());
        user.setCargo(
            cargoRepository.findById(dto.getIdCargo())
            .orElseThrow(() -> new RuntimeException("Cargo não encontrado"))
        );
        user.setDepartamento(
            departamentoRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Departamento não encontrado"))
        );

        user.setModelo(dto.getModeloTrabalho());
        
        user.setEscritorio(
            escritorioRepository.findById(dto.getIdEscritorio())
            .orElseThrow(() -> new RuntimeException("Escritorio não encontrado"))
        );

        user.setDataCadastro(dto.getDataCadastro());

        user.setAtivo(dto.isAtivo());

        Usuario atualizado = usuarioRepository.save(user);
        return usuarioMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Usuario user = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        
        usuarioRepository.delete(user);
    }
}