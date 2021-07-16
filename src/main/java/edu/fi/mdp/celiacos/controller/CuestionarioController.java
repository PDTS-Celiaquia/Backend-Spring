package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.auth.UsuarioWeb;
import edu.fi.mdp.celiacos.model.dto.CuestionarioDTO;
import edu.fi.mdp.celiacos.service.CuestionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Boolean celiaco
    ) {
        return ResponseEntity.ok(cuestionarioService.getAll(celiaco));
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(
            @RequestBody @Valid CuestionarioDTO cuestionario,
            @AuthenticationPrincipal UsuarioWeb user
    ) {
        return ResponseEntity.ok(cuestionarioService.nuevoCuestionario(cuestionario, user));
    }

}
