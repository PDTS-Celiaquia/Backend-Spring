package com.mau.spring.service;

import com.mau.spring.model.dto.CuestionarioDTO;
import com.mau.spring.model.entity.Cuestionario;
import com.mau.spring.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuestionarioService {
    CuestionarioRepository cuestionarioRepository;

    @Autowired
    public CuestionarioService(CuestionarioRepository cuestionarioRepository){
        this.cuestionarioRepository = cuestionarioRepository;
    }


    public Cuestionario nuevoCuestionario( CuestionarioDTO b) {
       Cuestionario c = new Cuestionario(b.getUserId(), b.isCeliaco(), b.getDiagnostico(), b.isTratamiento(), b.isDietaSinTACC(), b.getFrecuenciaTACC(), b.isVerduras(), b.getFrutas(), b.isCarne());
        return this.cuestionarioRepository.save(c);
    }

}
