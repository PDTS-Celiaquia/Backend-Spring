package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.auth.UsuarioWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioWebRepository extends JpaRepository<UsuarioWeb, Long> {
        Optional<UsuarioWeb> findByEmail(String email);
}
