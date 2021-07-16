package com.mau.spring.auth;

import com.google.common.collect.ImmutableMap;
import com.mau.spring.repository.UsuarioWebRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public final class TokenAuthenticationService implements UsuarioWebAuthService {
    private final JWTService tokenService;
    private final UsuarioWebRepository usuarioWebService;

    @Override
    public Optional<String> login(String email, String password) {
        return usuarioWebService.findByEmail(email)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokenService.expiring(ImmutableMap.of("email", email)));
    }

    @Override
    public Optional<UsuarioWeb> findByToken(String token) {
        return Optional.of(tokenService.verify(token))
                .map(map -> map.get("email"))
                .flatMap(usuarioWebService::findByEmail);
    }

    @Override
    public void logout(final UsuarioWeb user) {
        // Nothing to doy
    }
}