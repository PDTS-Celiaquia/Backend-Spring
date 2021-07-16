package edu.fi.mdp.celiacos.auth;

import java.util.Optional;


public interface UsuarioWebAuthService {
    Optional<String> login(String email, String password);

    Optional<UsuarioWeb> findByToken(String token);

    void logout(UsuarioWeb user);
}