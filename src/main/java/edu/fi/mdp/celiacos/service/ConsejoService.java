package edu.fi.mdp.celiacos.service;


import edu.fi.mdp.celiacos.model.entity.Consejo;
import edu.fi.mdp.celiacos.repository.ConsejoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ConsejoService {
    private final ConsejoRepository consejoRepository;

    @Autowired
    public ConsejoService(ConsejoRepository consejoRepository) {
        this.consejoRepository = consejoRepository;
    }

    public Consejo findById(Long id) {
        return consejoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Consejo saveConsejo(Consejo nuevoConsejo) {
        return consejoRepository.save(nuevoConsejo);
    }

    @Transactional
    public void deleteConsejo(Long idConsejo) {
        consejoRepository.deleteById(idConsejo);
    }


}
