package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.UtensilioRequestDTO;
import com.ti.estoque.dto.UtensilioResponseDTO;
import com.ti.estoque.mappers.UtensilioMapper;
import com.ti.estoque.model.Utensilio;
import com.ti.estoque.repository.MarcaRepository;
import com.ti.estoque.repository.ModeloRepository;
import com.ti.estoque.repository.TipoEquipamentoRepository;
import com.ti.estoque.repository.UtensilioRepository;

@Service
public class UtensilioService {

    private final ModeloRepository modeloRepository;

    private final MarcaRepository marcaRepository;

    private final TipoEquipamentoRepository tipoEquipamentoRepository;

    @Autowired
    private UtensilioRepository utensilioRepository;
    
    @Autowired
    private UtensilioMapper utensilioMapper;

    @SuppressWarnings("unused")
    UtensilioService(TipoEquipamentoRepository tipoEquipamentoRepository, MarcaRepository marcaRepository, ModeloRepository modeloRepository) {
        this.tipoEquipamentoRepository = tipoEquipamentoRepository;
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
    }
    
    // Método auxiliar para converter lista de entidades para lista de DTOs
    private List<UtensilioResponseDTO> converterParaDTO(List<Utensilio> entidades) {
        return entidades.stream()
                .map(utensilioMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UtensilioResponseDTO> findByTipoEquipamentoId(long id) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoId(id);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse tipo de equipamento (ID).");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> findByTipoEquipamentoDescricao(String descricao) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoDescricaoIgnoreCaseLike(descricao);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse tipo de equipamento (descrição).");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> findByMarca(String nomeMarca) {
        List<Utensilio> registros = utensilioRepository.findByMarcaNomeMarcaIgnoreCaseLike(nomeMarca);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essa marca.");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> findByModelo(String nomeModelo) {
        List<Utensilio> registros = utensilioRepository.findByModeloNomeModeloIgnoreCaseLike(nomeModelo);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esse modelo.");
        return converterParaDTO(registros);
    }

    // Métodos para múltiplos critérios (listas)
    public List<UtensilioResponseDTO> getTipoEquipamentoIds(List<Long> ids) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoIdIn(ids);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esses IDs de tipo de equipamento.");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> getTipoEquipamentoDescricoes(List<String> descricoes) {
        List<Utensilio> registros = utensilioRepository.findByTipoEquipamentoDescricaoIgnoreCaseIn(descricoes);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essas descrições de tipo de equipamento.");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> getMarcas(List<String> marcas) {
        List<Utensilio> registros = utensilioRepository.findByMarcaNomeMarcaIgnoreCaseIn(marcas);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com essas marcas.");
        return converterParaDTO(registros);
    }

    public List<UtensilioResponseDTO> getModelos(List<String> modelos) {
        List<Utensilio> registros = utensilioRepository.findByModeloNomeModeloIgnoreCaseIn(modelos);
        if (registros.isEmpty()) throw new RuntimeException("Nenhum utensílio encontrado com esses modelos.");
        return converterParaDTO(registros);
    }

    
    public UtensilioResponseDTO findById(Long id) {
        Utensilio utensilio = utensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Utensílio não encontrado com o ID: " + id));
        return utensilioMapper.toDto(utensilio);
    }
    
    public List<UtensilioResponseDTO> findAll() {
        List<Utensilio> registros = utensilioRepository.findAll();
        if (registros.isEmpty()) {
            throw new RuntimeException("Nenhum utensílio encontrado.");
        }
        return converterParaDTO(registros);
    }

    public UtensilioResponseDTO create(UtensilioRequestDTO dto) {
        Utensilio entidade = utensilioMapper.toEntity(dto);
        Utensilio salvo = utensilioRepository.save(entidade);
        return utensilioMapper.toDto(salvo);
    }
    
    public UtensilioResponseDTO update(UtensilioRequestDTO dto) {
        if (!utensilioRepository.existsById(dto.getId())) {
            throw new RuntimeException("Utensílio não encontrado com o ID: " + dto.getId());
        }
        
        Utensilio entidade = utensilioRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        entidade.setTipoEquipamento(
            tipoEquipamentoRepository.findById(dto.getIdTipoEquipamento())
            .orElseThrow(() -> new RuntimeException("Tipo de Equipamento não encontrado"))
        );

        entidade.setMarca(
            marcaRepository.findById(dto.getIdMarca())
            .orElseThrow(() -> new RuntimeException("Marca não encontrado"))
        );

        entidade.setModelo(
            modeloRepository.findById(dto.getIdModelo())
            .orElseThrow(() -> new RuntimeException("Modelo não encontrado"))
        );

        Utensilio atualizado = utensilioRepository.save(entidade);
        return utensilioMapper.toDto(atualizado);
    }

    public void delete(Long id){
        Utensilio item = utensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Utensilio não encontrado"));

        utensilioRepository.delete(item);
    }

}