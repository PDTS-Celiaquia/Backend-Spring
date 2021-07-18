package edu.fi.mdp.celiacos.repository;

import edu.fi.mdp.celiacos.auth.Authority;
import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {
}
