package edu.fi.mdp.celiacos.service;

import edu.fi.mdp.celiacos.auth.Authority;
import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import edu.fi.mdp.celiacos.auth.TokenAuthenticationService;
import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.exception.UnauthorizedException;
import edu.fi.mdp.celiacos.model.dto.LoginDTO;
import edu.fi.mdp.celiacos.model.dto.TokenDTO;
import edu.fi.mdp.celiacos.model.dto.UsuarioWebDTO;
import edu.fi.mdp.celiacos.repository.AuthorityRepository;
import edu.fi.mdp.celiacos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario register(UsuarioWebDTO usuarioWebDTO, AuthorityEnum role) {
        Authority authority = authorityRepository.findById(role).orElseThrow();
        Usuario usuario = new Usuario(usuarioWebDTO, authority);
        usuario.setPassword(passwordEncoder.encode(usuarioWebDTO.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public TokenDTO login(LoginDTO loginDTO) throws UnauthorizedException {
        Optional<String> stringOptional = tokenAuthenticationService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return new TokenDTO(stringOptional.orElseThrow(UnauthorizedException::new));
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
