package com.ti.estoque.mappers;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.TipoEquipamentoRequestDTO;
import com.ti.estoque.dto.TipoEquipamentoResponseDTO;
import com.ti.estoque.model.TipoEquipamento;

@Component
public class TipoEquipamentoMapper {

    public TipoEquipamento toEntity(TipoEquipamentoRequestDTO dto){
        TipoEquipamento entity = new TipoEquipamento();

        entity.setDescricao(dto.getDescricao());

        return entity;
    } 

    public TipoEquipamentoResponseDTO toDto(TipoEquipamento entity){
        TipoEquipamentoResponseDTO dto = new TipoEquipamentoResponseDTO();

        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());

        return dto;
    }
}
