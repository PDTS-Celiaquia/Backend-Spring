package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.model.entity.UnidadDeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadesRepository extends JpaRepository<UnidadDeMedida,Integer> {
}
