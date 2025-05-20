package com.ti.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ti.estoque.dto.MovimentacaoUtensilioRequestDTO;
import com.ti.estoque.dto.MovimentacaoUtensilioResponseDTO;
import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.mappers.MovimentacaoUtensilioMapper;
import com.ti.estoque.model.MovimentacaoUtensilio;
import com.ti.estoque.repository.MovimentacaoUtensilioRepository;
import com.ti.estoque.repository.UsuarioRepository;
import com.ti.estoque.repository.UtensilioRepository;

@Service
public class MovimentacaoUtensilioService {

    private final UsuarioRepository usuarioRepository;

    private final UtensilioRepository utensilioRepository;

    @Autowired
    private MovimentacaoUtensilioRepository movimentacaoUtensilioRepository;
    
    @Autowired
    private MovimentacaoUtensilioMapper movimentacaoUtensilioMapper;
    
    @SuppressWarnings("unused")
    MovimentacaoUtensilioService(UtensilioRepository utensilioRepository, UsuarioRepository usuarioRepository) {
        this.utensilioRepository = utensilioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @SuppressWarnings("unused")
    private List<MovimentacaoUtensilioResponseDTO> findAll(){
        List<MovimentacaoUtensilio> registros = movimentacaoUtensilioRepository.findAll();
        if (registros.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado.");
        }
        return registros.stream()
                .map(movimentacaoUtensilioMapper::toDto)
                .collect(Collectors.toList());
    }

    private <T> List<T> validarLista(List<T> lista, String mensagemErro) {
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagemErro);
        }
        return lista;
    }
    
    private List<MovimentacaoUtensilioResponseDTO> converterParaDTO(List<MovimentacaoUtensilio> entidades) {
        return entidades.stream()
                .map(movimentacaoUtensilioMapper::toDto)
                .collect(Collectors.toList());
    }

    public MovimentacaoUtensilioResponseDTO findById(Long id){
        MovimentacaoUtensilio moviment = movimentacaoUtensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimentacao não encontrado"));

        return movimentacaoUtensilioMapper.toDto(moviment);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUtensilioId(Long id) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioId(id),
            "Não foram encontradas movimentações para o ID informado."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByTipoEquipamento(String tipoEquipamento) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioTipoEquipamentoDescricaoIgnoreCaseLike(tipoEquipamento),
            "Não foram encontrados utensílios desse tipo."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByMarca(String marca) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioMarcaNomeMarcaIgnoreCaseLike(marca),
            "Não foram encontrados utensílios com essa marca."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByModelo(String modelo) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioModeloNomeModeloIgnoreCaseLike(modelo),
            "Não foram encontrados utensílios com esse modelo."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByTipoMovimentacao(tipoMovimentacao),
            "Não foram encontradas movimentações desse tipo."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUsuarioId(Long id) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUsuarioId(id),
            "Não foram encontradas movimentações para este usuário."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUsuarioNome(String nome) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUsuarioNomeIgnoreCaseLike(nome),
            "Não foram encontradas movimentações para o nome informado."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUtensilioIds(List<Long> ids) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioIdIn(ids),
            "Não foram encontrados utensílios com os IDs fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByTiposEquipamentos(List<String> tipos) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioTipoEquipamentoDescricaoIgnoreCaseIn(tipos),
            "Não foram encontrados utensílios com os tipos fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByMarcas(List<String> marcas) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioMarcaNomeMarcaIgnoreCaseIn(marcas),
            "Não foram encontrados utensílios com as marcas fornecidas."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByModelos(List<String> modelos) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioModeloNomeModeloIgnoreCaseIn(modelos),
            "Não foram encontrados utensílios com os modelos fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByTiposMovimentacao(List<TipoMovimentacao> tipos) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByTipoMovimentacaoIn(tipos),
            "Não foram encontradas movimentações dos tipos fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUsuarioIds(List<Long> ids) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUsuarioIdIn(ids),
            "Não foram encontradas movimentações para os IDs de usuário fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByUsuarios(List<String> nomes) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUsuarioNomeIgnoreCaseIn(nomes),
            "Não foram encontradas movimentações para os nomes de usuário fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public MovimentacaoUtensilioResponseDTO create(MovimentacaoUtensilioRequestDTO dto) {
        MovimentacaoUtensilio entidade = movimentacaoUtensilioMapper.toEntity(dto);
        MovimentacaoUtensilio salva = movimentacaoUtensilioRepository.save(entidade);
        return movimentacaoUtensilioMapper.toDto(salva);
    }

    public MovimentacaoUtensilioResponseDTO update(MovimentacaoUtensilioRequestDTO dto){
        MovimentacaoUtensilio moviment = movimentacaoUtensilioRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("movimentação não encontrada"));

        moviment.setUtensilio(
            utensilioRepository.findById(dto.getIdUtensilio())
            .orElseThrow(() -> new RuntimeException("Utensilio não encontrado"))
        );

        moviment.setQuantidade(dto.getQuantidade());

        moviment.setTipoMovimentacao(dto.getTipoMovimentacao());

        moviment.setUsuario(
            usuarioRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"))
        );

        moviment.setDataMovimentacao(dto.getDataMovimentacao());

        MovimentacaoUtensilio atualizada = movimentacaoUtensilioRepository.save(moviment);

        return movimentacaoUtensilioMapper.toDto(atualizada);
    }

    public void delete(Long id){
        MovimentacaoUtensilio moviment = movimentacaoUtensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacaoUtensilioRepository.delete(moviment);
    }
}