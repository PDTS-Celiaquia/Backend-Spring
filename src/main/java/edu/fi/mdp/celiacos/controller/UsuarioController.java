package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.auth.AuthorityEnum;
import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.exception.UnauthorizedException;
import edu.fi.mdp.celiacos.model.dto.LoginDTO;
import edu.fi.mdp.celiacos.model.dto.PasswordDTO;
import edu.fi.mdp.celiacos.model.dto.UsuarioDTO;
import edu.fi.mdp.celiacos.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioDTO usuarioDTO) throws UnauthorizedException {
        usuarioService.register(usuarioDTO, AuthorityEnum.PACIENTE);
        return ResponseEntity.ok(usuarioService.login(usuarioDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) throws UnauthorizedException {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }

    @PatchMapping("/modifyPassword")
    public ResponseEntity<?> modifyPassword(
            @RequestBody @Valid PasswordDTO passwordDTO, @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(usuarioService.modifyPassword(usuario, passwordDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registerOperario")
    public ResponseEntity<?> registerOperario(@RequestBody @Valid UsuarioDTO usuarioDTO) throws UnauthorizedException {
        return ResponseEntity.ok(usuarioService.register(usuarioDTO, AuthorityEnum.OPERARIO));
    }
}

