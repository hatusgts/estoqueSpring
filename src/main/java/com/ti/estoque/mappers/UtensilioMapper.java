package com.ti.estoque.mappers;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.UtensilioRequestDTO;
import com.ti.estoque.dto.UtensilioResponseDTO;
import com.ti.estoque.model.Marca;
import com.ti.estoque.model.Modelo;
import com.ti.estoque.model.TipoEquipamento;
import com.ti.estoque.model.Utensilio;
import com.ti.estoque.repository.MarcaRepository;
import com.ti.estoque.repository.ModeloRepository;
import com.ti.estoque.repository.TipoEquipamentoRepository;

@Component
public class UtensilioMapper {

    private final TipoEquipamentoRepository tipoEquipamentoRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    public UtensilioMapper(
        TipoEquipamentoRepository tipoEquipamentoRepository,
        MarcaRepository marcaRepository,
        ModeloRepository modeloRepository
    ){
        this.tipoEquipamentoRepository = tipoEquipamentoRepository;
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
    }

    public Utensilio toEntity(UtensilioRequestDTO dto){
        Utensilio entity = new Utensilio();

        TipoEquipamento tipoEquipamento = tipoEquipamentoRepository.findById(dto.getIdTipoEquipamento())
        .orElseThrow(() -> new RuntimeException("Tipo de Equipamento não encontrado"));

        Marca marca = marcaRepository.findById(dto.getIdMarca())
        .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        Modelo modelo = modeloRepository.findById(dto.getIdModelo())
        .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        entity.setTipoEquipamento(tipoEquipamento);
        entity.setMarca(marca);
        entity.setModelo(modelo);

        return entity;
    }

    public UtensilioResponseDTO toDto(Utensilio entity){
        UtensilioResponseDTO dto = new UtensilioResponseDTO();

        dto.setId(entity.getId());
        dto.setTipoEquipamento(entity.getTipoEquipamento().getDescricao());
        dto.setMarca(entity.getMarca().getNomeMarca());
        dto.setModelo(entity.getModelo().getNomeModelo());

        return dto;
    }
}
