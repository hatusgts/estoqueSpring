package com.ti.estoque.mappers;

import com.ti.estoque.dto.CargoRequestDTO;
import com.ti.estoque.dto.CargoResponseDTO;
import com.ti.estoque.model.Cargo;

public class CargoMapper {
    public static Cargo toEntity(CargoRequestDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNomeCargo(dto.getNomeCargo());
        return cargo;
    }

    public static CargoResponseDTO toDto(Cargo entity) {
        CargoResponseDTO dto = new CargoResponseDTO();
        dto.setId(entity.getId());
        dto.setNomeCargo(entity.getNomeCargo());
        return dto;
    }
}
