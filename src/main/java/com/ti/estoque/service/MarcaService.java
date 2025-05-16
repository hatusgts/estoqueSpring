package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Marca;
import com.ti.estoque.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll(){
        List<Marca> marcas = marcaRepository.findAll();

        if(marcas.isEmpty()){
            throw new RuntimeException("Não há Marcas Cadastradas");
        }

        return marcas;
    }

    public List<Marca> findByIdIn(List<Long> id){
        List<Marca> marcas = marcaRepository.findByIdIn(id);

        if(marcas.isEmpty()) throw new RuntimeException("Não foram encontradas Marcas nesses ID's");

        return marcas;
    }

    public List<Marca> findByNomeMarca(List<String> marca){
        List<Marca> marcas = marcaRepository.findByNomeMarcaIgnoreCaseIn(marca);

        if(marcas.isEmpty()) {
            throw new RuntimeException("Marcas não encontradas");
        }

        return marcas;
    }

    public Marca saveMarca(Marca item){
        return marcaRepository.save(item);
    }
}
