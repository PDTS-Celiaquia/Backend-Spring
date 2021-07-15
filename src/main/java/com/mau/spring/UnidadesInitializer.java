package com.mau.spring;

import com.mau.spring.model.entity.UnidadDeMedida;
import com.mau.spring.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UnidadesInitializer implements ApplicationRunner {
    private final UnidadesRepository unidadesRepository;

    @Autowired
    public UnidadesInitializer(UnidadesRepository unidadesRepository){
        this.unidadesRepository = unidadesRepository;
    }

    public void run(ApplicationArguments args){
        unidadesRepository.save(new UnidadDeMedida(0, "Gramo"));
        unidadesRepository.save(new UnidadDeMedida(1, "Litro"));
        unidadesRepository.save(new UnidadDeMedida(2, "Cucharada sopera"));
        unidadesRepository.save(new UnidadDeMedida(3, "Cucharada de té"));
        unidadesRepository.save(new UnidadDeMedida(4, "Taza"));
        unidadesRepository.save(new UnidadDeMedida(5, "Unidad"));

    }
}
