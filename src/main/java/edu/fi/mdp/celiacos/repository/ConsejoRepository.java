package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.model.entity.Consejo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsejoRepository extends JpaRepository<Consejo, Long> {
    List<Consejo> findById(long id);
}
