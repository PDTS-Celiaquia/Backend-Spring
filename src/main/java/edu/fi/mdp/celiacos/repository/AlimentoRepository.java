package edu.fi.mdp.celiacos.repository;


import edu.fi.mdp.celiacos.model.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    List<Alimento> findByNombre(String nombre);

    @Modifying
    @Query(value = "UPDATE Alimento a SET a.esAccesible = :esAccesible WHERE a.numero = :alimentoId")
    void updateAlimentoEsAccesible(
            @Param("alimentoId") Long alimentoId, @Param("esAccesible") boolean esAccesible);
}
