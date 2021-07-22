package edu.fi.mdp.celiacos.service;


import edu.fi.mdp.celiacos.model.entity.Receta;
import edu.fi.mdp.celiacos.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> getAll(String name) {
        if (isNull(name))
            return recetaRepository.findAll();
        else
            return recetaRepository.findByNombre(name);
    }

    @Transactional
    public Receta saveReceta(Receta nuevaReceta) {
        // TODO: calculate other fields
        return recetaRepository.save(nuevaReceta);
    }

    @Transactional
    public void deleteReceta(Integer idReceta) {
        recetaRepository.deleteById(idReceta);
    }


}
