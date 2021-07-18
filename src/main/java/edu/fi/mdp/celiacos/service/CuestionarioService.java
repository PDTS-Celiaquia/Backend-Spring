package edu.fi.mdp.celiacos.service;

import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.model.dto.CuestionarioDTO;
import edu.fi.mdp.celiacos.model.entity.Cuestionario;
import edu.fi.mdp.celiacos.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CuestionarioService {
    CuestionarioRepository cuestionarioRepository;

    @Autowired
    public CuestionarioService(CuestionarioRepository cuestionarioRepository){
        this.cuestionarioRepository = cuestionarioRepository;
    }

    @Transactional
    public Cuestionario nuevoCuestionario(CuestionarioDTO b, Usuario user) {
        return this.cuestionarioRepository.save(new Cuestionario(user, b));
    }

    public List<Cuestionario> getAll(Boolean celiaco) {
        if (celiaco == null) {
            return cuestionarioRepository.findAll();
        } else {
            return cuestionarioRepository.findAllByCeliaco(celiaco);
        }
    }
}
