package com.mau.spring.service;

import com.mau.spring.model.entity.UnidadDeMedida;
import com.mau.spring.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadesService {
    private final UnidadesRepository unidadesRepository;

    @Autowired
    public UnidadesService(UnidadesRepository unidadesRepository) {
        this.unidadesRepository = unidadesRepository;
    }

    public List<UnidadDeMedida> getAll(){
        return unidadesRepository.findAll();
    }

    public UnidadDeMedida addUnidad(UnidadDeMedida nuevaUnidad) {
        return unidadesRepository.save(nuevaUnidad);
    }
}
