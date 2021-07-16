package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.model.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository  extends JpaRepository<Receta,Integer> {
    List<Receta> findByNombre(String nombre);
}
