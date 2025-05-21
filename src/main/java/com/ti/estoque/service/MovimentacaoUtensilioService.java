package com.ti.estoque.service;

import java.util.ArrayList;
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
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UtensilioRepository utensilioRepository;

    @Autowired
    private MovimentacaoUtensilioRepository movimentacaoUtensilioRepository;
    
    @Autowired
    private MovimentacaoUtensilioMapper movimentacaoUtensilioMapper;

    public List<MovimentacaoUtensilioResponseDTO> findAll(){
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


    //Começo dos buscadores
    public MovimentacaoUtensilioResponseDTO findById(Long id){
        MovimentacaoUtensilio moviment = movimentacaoUtensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimentacao não encontrado"));

        return movimentacaoUtensilioMapper.toDto(moviment);
    }

    public List<MovimentacaoUtensilioResponseDTO> findByIds(List<Long> ids) {
        List<MovimentacaoUtensilio> movimentacoesEncontradas = new ArrayList<>();

        for (Long id : ids) {
            movimentacaoUtensilioRepository.findById(id)
                .ifPresent(movimentacoesEncontradas::add);
        }

        if (movimentacoesEncontradas.isEmpty()) {
            throw new RuntimeException("Nenhuma movimentação encontrada com os IDs fornecidos.");
        }

        return movimentacoesEncontradas.stream()
                .map(movimentacaoUtensilioMapper::toDto)
                .collect(Collectors.toList());
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

    //Buscas de listas, que retornam listas
    public List<MovimentacaoUtensilioResponseDTO> getUtensilioIds(List<Long> ids) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUtensilioIdIn(ids),
            "Não foram encontrados utensílios com os IDs fornecidos."
        );
        return converterParaDTO(entidades);
    }

    public List<MovimentacaoUtensilioResponseDTO> getByTiposEquipamentos(List<String> tipos) {
        List<MovimentacaoUtensilio> entidades = new ArrayList<>();

        for (String tipo : tipos) {
            entidades.addAll(movimentacaoUtensilioRepository
                .findByUtensilioTipoEquipamentoDescricaoIgnoreCaseLike(tipo));
        }

        return converterParaDTO(validarLista(entidades, 
            "Não foram encontrados utensílios com os tipos fornecidos."));
    }

    public List<MovimentacaoUtensilioResponseDTO> getByMarcas(List<String> marcas) {
        List<MovimentacaoUtensilio> entidades = new ArrayList<>();

        for (String marca : marcas) {
            entidades.addAll(movimentacaoUtensilioRepository
                .findByUtensilioMarcaNomeMarcaIgnoreCaseLike(marca));
        }

        return converterParaDTO(validarLista(entidades, 
            "Não foram encontrados utensílios com as marcas fornecidas."));
    }

    public List<MovimentacaoUtensilioResponseDTO> getByModelos(List<String> modelos) {
        List<MovimentacaoUtensilio> entidades = new ArrayList<>();

        for (String modelo : modelos) {
            entidades.addAll(movimentacaoUtensilioRepository
                .findByUtensilioModeloNomeModeloIgnoreCaseLike(modelo));
        }

        return converterParaDTO(validarLista(entidades, 
            "Não foram encontrados utensílios com os modelos fornecidos."));
    }

    public List<MovimentacaoUtensilioResponseDTO> getByTiposMovimentacao(List<TipoMovimentacao> tipos) {
        List<MovimentacaoUtensilio> entidades = new ArrayList<>();

        for (TipoMovimentacao tipo : tipos) {
            entidades.addAll(movimentacaoUtensilioRepository
                .findByTipoMovimentacao(tipo));
        }

        return converterParaDTO(validarLista(entidades, 
            "Não foram encontradas movimentações dos tipos fornecidos."));
    }

    public List<MovimentacaoUtensilioResponseDTO> getByUsuarios(List<String> nomes) {
        List<MovimentacaoUtensilio> entidades = new ArrayList<>();

        for (String nome : nomes) {
            entidades.addAll(movimentacaoUtensilioRepository
                .findByUsuarioNomeIgnoreCaseLike(nome));
        }

        return converterParaDTO(validarLista(entidades, 
            "Não foram encontradas movimentações para os nomes de usuário fornecidos."));
    }

    public List<MovimentacaoUtensilioResponseDTO> getByUsuarioIds(List<Long> ids) {
        List<MovimentacaoUtensilio> entidades = validarLista(
            movimentacaoUtensilioRepository.findByUsuarioIdIn(ids),
            "Não foram encontradas movimentações para os IDs de usuário fornecidos."
        );
        return converterParaDTO(entidades);
    }

    //Create, update, delete
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

    public void deleteId(Long id){
        MovimentacaoUtensilio moviment = movimentacaoUtensilioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacaoUtensilioRepository.delete(moviment);
    }

    public void deleteIds(List<Long> ids) {
        List<MovimentacaoUtensilio> movimentacoes = movimentacaoUtensilioRepository.findAllById(ids);

        if (movimentacoes.isEmpty()) {
            throw new RuntimeException("Nenhuma movimentação de utensílio encontrada com os IDs fornecidos.");
        }

        movimentacaoUtensilioRepository.deleteAll(movimentacoes);
    }

}