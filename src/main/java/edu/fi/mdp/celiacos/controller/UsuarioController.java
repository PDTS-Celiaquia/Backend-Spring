package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import edu.fi.mdp.celiacos.exception.UnauthorizedException;
import edu.fi.mdp.celiacos.model.dto.LoginDTO;
import edu.fi.mdp.celiacos.model.dto.UsuarioWebDTO;
import edu.fi.mdp.celiacos.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioWebDTO body) throws UnauthorizedException {
        usuarioService.register(body, AuthorityEnum.PACIENTE);
        return ResponseEntity.ok(usuarioService.login(body));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO body) throws UnauthorizedException {
        return ResponseEntity.ok(usuarioService.login(body));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registerOperario")
    public ResponseEntity<?> registerOperario(@RequestBody @Valid UsuarioWebDTO body) {
        return ResponseEntity.ok(usuarioService.register(body, AuthorityEnum.OPERARIO));
    }
}

