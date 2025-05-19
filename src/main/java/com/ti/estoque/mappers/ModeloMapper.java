package com.ti.estoque.mappers;

import com.ti.estoque.dto.ModeloRequestDTO;
import com.ti.estoque.dto.ModeloResponseDTO;
import com.ti.estoque.model.Modelo;


public class ModeloMapper {

    public static Modelo toEntity(ModeloRequestDTO dto){
        Modelo entity = new Modelo();

        entity.setNomeModelo(dto.getNomeModelo());

        return entity;
    }

    public static ModeloResponseDTO toDto(Modelo entity){
        ModeloResponseDTO dto = new ModeloResponseDTO();

        dto.setIdModelo(entity.getId());
        dto.setNomeModelo(entity.getNomeModelo());

        return dto;
    }
}
