package com.ti.estoque.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.EquipamentoRequestDTO;
import com.ti.estoque.dto.EquipamentoResponseDTO;
import com.ti.estoque.model.Equipamento;
import com.ti.estoque.model.Marca;
import com.ti.estoque.model.Modelo;
import com.ti.estoque.model.TipoEquipamento;
import com.ti.estoque.repository.MarcaRepository;
import com.ti.estoque.repository.ModeloRepository;
import com.ti.estoque.repository.TipoEquipamentoRepository;

@Component
public class EquipamentoMapper {

    private final TipoEquipamentoRepository tipoEquipamentoRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    public EquipamentoMapper(
        TipoEquipamentoRepository tipoEquipamentoRepository,
        MarcaRepository marcaRepository,
        ModeloRepository modeloRepository
    ) {
        this.tipoEquipamentoRepository = tipoEquipamentoRepository;
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
    }

    public Equipamento toEntity(EquipamentoRequestDTO dto){
        Equipamento entity = new Equipamento();
        entity.setNumeroPatrimonio(dto.getNumeroPatrimonio());

        TipoEquipamento tipo = tipoEquipamentoRepository.findById(dto.getTipoEquipamentoID())
            .orElseThrow(() -> new RuntimeException("TipoEquipamento não encontrado"));

        Marca marca = marcaRepository.findById(dto.getIdMarca())
            .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        
        Modelo modelo = modeloRepository.findById(dto.getIdModelo())
        .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        entity.setTipoEquipamento(tipo);
        entity.setMarca(marca);
        entity.setModelo(modelo);
        entity.setServiceTag(dto.getServiceTag());

        if(dto.getData() != null){
            entity.setDataCadastro(entity.getDataCadastro());
        }else{
            entity.setDataCadastro(LocalDate.now());
        }

        return entity;
    }

    public EquipamentoResponseDTO toDto(Equipamento entity){
        EquipamentoResponseDTO dto = new EquipamentoResponseDTO();

        TipoEquipamento tipo = entity.getTipoEquipamento();
        Marca marca = entity.getMarca();
        Modelo modelo = entity.getModelo();

        dto.setId(entity.getId());
        dto.setNumeroPatrimonio(entity.getNumeroPatrimonio());
        dto.setTipoEquipamento(tipo.getDescricao());
        dto.setMarca(marca.getNomeMarca());
        dto.setModelo(modelo.getNomeModelo());
        dto.setServiceTag(entity.getServiceTag());
        dto.setDataCadastro(entity.getDataCadastro());

        return dto;
    }
}
