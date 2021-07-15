package com.mau.spring.auth;

import java.util.Optional;


/**
 * Interfaz para clase responsable de la autenticacion de los usuarios (verifica que los tokens sean válidos)
 */
public interface UsuarioWebAuthService {

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param email
     * @param password
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<String> login(String email, String password);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     * @return
     */
    Optional<UsuarioWeb> findByToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(UsuarioWeb user);
}