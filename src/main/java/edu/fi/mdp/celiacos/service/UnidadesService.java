package edu.fi.mdp.celiacos.service;

import edu.fi.mdp.celiacos.model.entity.UnidadDeMedida;
import edu.fi.mdp.celiacos.repository.UnidadesRepository;
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
