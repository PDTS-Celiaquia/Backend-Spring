package edu.fi.mdp.celiacos.service;

import edu.fi.mdp.celiacos.auth.Authority;
import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import edu.fi.mdp.celiacos.auth.TokenAuthenticationService;
import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.exception.EmailNotAvailableException;
import edu.fi.mdp.celiacos.exception.UnauthorizedException;
import edu.fi.mdp.celiacos.model.dto.LoginDTO;
import edu.fi.mdp.celiacos.model.dto.PasswordDTO;
import edu.fi.mdp.celiacos.model.dto.LoginResponseDTO;
import edu.fi.mdp.celiacos.model.dto.UsuarioDTO;
import edu.fi.mdp.celiacos.repository.AuthorityRepository;
import edu.fi.mdp.celiacos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario register(UsuarioDTO usuarioDTO, AuthorityEnum role) throws EmailNotAvailableException {
        Authority authority = authorityRepository.findById(role).orElseThrow();
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) throw new EmailNotAvailableException();
        Usuario usuario = new Usuario(usuarioDTO, authority);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) throws UnauthorizedException {
        Optional<String> stringOptional = tokenAuthenticationService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return new LoginResponseDTO(stringOptional.orElseThrow(UnauthorizedException::new));
    }

    @Transactional
    public boolean modifyPassword(Usuario usuario, PasswordDTO passwordDTO) {
        usuarioRepository.updatePasswordById(
                passwordEncoder.encode(passwordDTO.getPassword()), usuario.getId());
        return true;
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
