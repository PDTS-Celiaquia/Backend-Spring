package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.model.entity.Cuestionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuestionarioRepository extends JpaRepository<Cuestionario, Long> {
    List<Cuestionario> findAllByCeliaco(boolean celiaco);
}
