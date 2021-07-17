package edu.fi.mdp.celiacos;

import edu.fi.mdp.celiacos.auth.Authority;
import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import edu.fi.mdp.celiacos.model.entity.UnidadDeMedida;
import edu.fi.mdp.celiacos.repository.AuthorityRepository;
import edu.fi.mdp.celiacos.repository.UnidadesRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UnidadesInitializer implements ApplicationRunner {
    private final UnidadesRepository unidadesRepository;
    private final AuthorityRepository authorityRepository;

    public void run(ApplicationArguments args){
        unidadesRepository.save(new UnidadDeMedida(0, "Gramo"));
        unidadesRepository.save(new UnidadDeMedida(1, "Litro"));
        unidadesRepository.save(new UnidadDeMedida(2, "Cucharada sopera"));
        unidadesRepository.save(new UnidadDeMedida(3, "Cucharada de té"));
        unidadesRepository.save(new UnidadDeMedida(4, "Taza"));
        unidadesRepository.save(new UnidadDeMedida(5, "Unidad"));

        authorityRepository.save(new Authority(AuthorityEnum.ADMIN));
        authorityRepository.save(new Authority(AuthorityEnum.OPERARIO));
        authorityRepository.save(new Authority(AuthorityEnum.PACIENTE));
    }
}
