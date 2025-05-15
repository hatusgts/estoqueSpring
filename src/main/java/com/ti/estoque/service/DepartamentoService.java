package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Departamento;
import com.ti.estoque.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll(){
        return departamentoRepository.findAll();
    }

    public List<Departamento> findID(List<Long> ids){
        return departamentoRepository.findByIdIn(ids);
    }

    public List<Departamento> findDepartamentos(List<String> departamento){
        return departamentoRepository.findByNomeDepartamentoIgnoreCaseIn(departamento);
    }
}
