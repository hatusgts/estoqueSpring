package com.ti.estoque.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.MovimentacaoEquipamentoRequestDTO;
import com.ti.estoque.dto.MovimentacaoEquipamentoResponseDTO;
import com.ti.estoque.model.Equipamento;
import com.ti.estoque.model.MovimentacaoEquipamento;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.repository.EquipamentoRepository;
import com.ti.estoque.repository.UsuarioRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class MovimentacaoEquipamentoMapper {

    private final EquipamentoRepository equipamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimentacaoEquipamentoMapper(
        EquipamentoRepository equipamentoRepository,
        UsuarioRepository usuarioRepository
    ){
        this.equipamentoRepository = equipamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public MovimentacaoEquipamento toEntity(MovimentacaoEquipamentoRequestDTO dto){
        
        MovimentacaoEquipamento entity = new MovimentacaoEquipamento();

        Equipamento equipamento = equipamentoRepository.findById(dto.getIdEquipamento())
        .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        Usuario user = usuarioRepository.findById(dto.getIdUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        entity.setEquipamento(equipamento);
        entity.setTipoMovimentacao(dto.getTipoMovimentacao());
        entity.setUsuario(user);
        if(dto.getDataMovimentacao() != null){
            entity.setDataMovimentacao(dto.getDataMovimentacao());
        }else{
            entity.setDataMovimentacao(LocalDate.now());
        }

        return entity;
    }

    public MovimentacaoEquipamentoResponseDTO toDto(MovimentacaoEquipamento entity){
        MovimentacaoEquipamentoResponseDTO dto = new MovimentacaoEquipamentoResponseDTO();

        dto.setIdMovimentacao(entity.getId());
        dto.setEquipamento(entity.getEquipamento().getNumeroPatrimonio());
        dto.setTipoMovimentacao(entity.getTipoMovimentacao().name());
        dto.setUsuario(entity.getUsuario().getNome());
        dto.setDataMovimentacao(entity.getDataMovimentacao());

        return dto;
    }
}
