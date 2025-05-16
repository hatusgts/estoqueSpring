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

    public List<Departamento> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado.");
        }
        return departamentos;
    }

    public List<Departamento> findID(List<Long> ids) {
        List<Departamento> departamentos = departamentoRepository.findByIdIn(ids);
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado com os IDs fornecidos.");
        }
        return departamentos;
    }

    public List<Departamento> findDepartamentos(List<String> departamento) {
        List<Departamento> departamentos = departamentoRepository.findByNomeDepartamentoIgnoreCaseIn(departamento);
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado com os nomes fornecidos.");
        }
        return departamentos;
    }

    public Departamento saveDepartamento(Departamento item){
        return departamentoRepository.save(item);
    }
}