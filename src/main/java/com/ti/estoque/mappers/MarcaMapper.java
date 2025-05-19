package com.ti.estoque.mappers;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.MarcaRequestDTO;
import com.ti.estoque.dto.MarcaResponseDTO;
import com.ti.estoque.model.Marca;

@Component
public class MarcaMapper {

    public Marca toEntity(MarcaRequestDTO dto){
        Marca entity = new Marca();

        entity.setNomeMarca(dto.getNomeMarca());
        
        return entity;
    }

    public MarcaResponseDTO toDto(Marca entity){
        MarcaResponseDTO dto = new MarcaResponseDTO();

        dto.setId(entity.getId());
        dto.setNomeMarca(entity.getNomeMarca());

        return dto;
    }
}
