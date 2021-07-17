package edu.fi.mdp.celiacos.auth;

import edu.fi.mdp.celiacos.repository.UsuarioRepository;
import edu.fi.mdp.celiacos.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getAuthorities());
    }
}
