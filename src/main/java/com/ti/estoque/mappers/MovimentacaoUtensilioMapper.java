package com.ti.estoque.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ti.estoque.dto.MovimentacaoUtensilioRequestDTO;
import com.ti.estoque.dto.MovimentacaoUtensilioResponseDTO;
import com.ti.estoque.model.MovimentacaoUtensilio;
import com.ti.estoque.model.Usuario;
import com.ti.estoque.model.Utensilio;
import com.ti.estoque.repository.UsuarioRepository;
import com.ti.estoque.repository.UtensilioRepository;

@Component
public class MovimentacaoUtensilioMapper {

    private final UsuarioRepository usuarioRepository;
    private final UtensilioRepository utensilioRepository;
    
    public MovimentacaoUtensilioMapper(
        UsuarioRepository usuarioRepository,
        UtensilioRepository utensilioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.utensilioRepository = utensilioRepository;
    }

    public MovimentacaoUtensilio toEntity(MovimentacaoUtensilioRequestDTO dto){
        MovimentacaoUtensilio entity = new MovimentacaoUtensilio();

        Utensilio utensilio = utensilioRepository.findById(dto.getIdUtensilio())
            .orElseThrow(() -> new RuntimeException("Utensilio não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        entity.setUtensilio(utensilio);
        entity.setQuantidade(dto.getQuantidade());

        entity.setTipoMovimentacao(dto.getTipoMovimentacao());

        entity.setUsuario(usuario);

        if(dto.getDataMovimentacao() != null){
            entity.setDataMovimentacao(dto.getDataMovimentacao());
        } else {
            entity.setDataMovimentacao(LocalDate.now());
        }

        return entity;
    }


    public MovimentacaoUtensilioResponseDTO toDto(MovimentacaoUtensilio entity){
        MovimentacaoUtensilioResponseDTO dto = new MovimentacaoUtensilioResponseDTO();

        dto.setId(entity.getId());

        Utensilio utensilio = entity.getUtensilio();
        String descricaoUtensilio = "ID: " + utensilio.getId();

        dto.setUtensilio(descricaoUtensilio);

        dto.setQuantidade(entity.getQuantidade());
        dto.setTipoMovimentacao(entity.getTipoMovimentacao().toString());
        dto.setUsuario(entity.getUsuario().getNome());
        dto.setDataMovimentacao(entity.getDataMovimentacao());

        return dto;
    }
}
