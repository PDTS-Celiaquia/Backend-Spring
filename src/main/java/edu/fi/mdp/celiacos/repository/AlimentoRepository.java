package edu.fi.mdp.celiacos.repository;


import edu.fi.mdp.celiacos.model.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    List<Alimento> findByNombre(String nombre);
}
