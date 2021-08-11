package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.model.dto.request.CuestionarioDTO;
import edu.fi.mdp.celiacos.service.CuestionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/cuestionario")
public class CuestionarioController {
    private final CuestionarioService cuestionarioService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Boolean celiaco
    ) {
        return ResponseEntity.ok(cuestionarioService.getAll(celiaco));
    }

    @PreAuthorize("hasAuthority('PACIENTE')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(
            @RequestBody @Valid CuestionarioDTO cuestionario,
            @AuthenticationPrincipal Usuario user
    ) {
        return ResponseEntity.ok(cuestionarioService.nuevoCuestionario(cuestionario, user));
    }

}
