package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE Usuario u " +
            "SET u.password = :password " +
            "WHERE u.id = :id")
    void updatePasswordById(@Param("password") String password, @Param("id") Long id);
}
