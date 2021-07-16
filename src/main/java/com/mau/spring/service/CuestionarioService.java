package com.mau.spring.service;

import com.mau.spring.auth.UsuarioWeb;
import com.mau.spring.model.dto.CuestionarioDTO;
import com.mau.spring.model.entity.Cuestionario;
import com.mau.spring.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuestionarioService {
    CuestionarioRepository cuestionarioRepository;

    @Autowired
    public CuestionarioService(CuestionarioRepository cuestionarioRepository){
        this.cuestionarioRepository = cuestionarioRepository;
    }


    public Cuestionario nuevoCuestionario(CuestionarioDTO b, UsuarioWeb user) {
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
