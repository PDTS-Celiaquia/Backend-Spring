package edu.fi.mdp.celiacos.auth;

import com.google.common.collect.ImmutableMap;
import edu.fi.mdp.celiacos.service.UsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class TokenAuthenticationService {
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    public TokenAuthenticationService(
            JWTService jwtService,
            PasswordEncoder passwordEncoder,
            @Lazy UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    public Optional<String> login(String email, String password) {
        return usuarioService.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtService.expiring(
                        ImmutableMap.of("email", user.getEmail(), "role", user.getRoleName())
                ));
    }

    public Optional<Usuario> findByToken(String token) {
        return Optional.of(jwtService.verify(token))
                .map(map -> map.get("email"))
                .flatMap(usuarioService::findByEmail);
    }
}