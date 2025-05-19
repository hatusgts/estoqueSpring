package com.ti.estoque.mappers;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.EscritorioRequestDTO;
import com.ti.estoque.dto.EscritorioResponseDTO;
import com.ti.estoque.model.Escritorio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EscritorioMapper {

    public Escritorio toEntity(EscritorioRequestDTO dto){
        Escritorio entity = new Escritorio();

        entity.setNomeEscritorio(dto.getNomeEscritorio());

        return entity;
    }

    public EscritorioResponseDTO toDto(Escritorio entity){
        EscritorioResponseDTO dto = new EscritorioResponseDTO();

        dto.setEscritorioID(entity.getId());
        dto.setNomeEscritorio(entity.getNomeEscritorio());

        return dto;
    }
}
