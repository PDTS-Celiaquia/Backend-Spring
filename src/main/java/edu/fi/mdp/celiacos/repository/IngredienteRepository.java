package edu.fi.mdp.celiacos.repository;


import edu.fi.mdp.celiacos.model.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {
}
