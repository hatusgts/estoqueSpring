package com.ti.estoque.mappers;

import com.ti.estoque.dto.DepartamentoRequestDTO;
import com.ti.estoque.dto.DepartamentoResponseDTO;
import com.ti.estoque.model.Departamento;

public class DepartamentoMapper {
    public static Departamento toEntity(DepartamentoRequestDTO dto){
        Departamento departamento = new Departamento();
        departamento.setNomeDepartamento(dto.getNomeDepartamento());
        return departamento;
    }

    public static DepartamentoResponseDTO toDto(Departamento entity){
        DepartamentoResponseDTO dto = new DepartamentoResponseDTO();
        dto.setId(entity.getId());
        dto.setNomeDepartamento(entity.getNomeDepartamento());
        return dto;
    }
}
