package edu.fi.mdp.celiacos.auth;

import com.google.common.collect.ImmutableMap;
import edu.fi.mdp.celiacos.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public final class TokenAuthenticationService {
    private final JWTService jwtService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

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