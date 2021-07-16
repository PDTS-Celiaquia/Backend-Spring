package com.mau.spring.controller;

import com.mau.spring.auth.UsuarioWeb;
import com.mau.spring.auth.UsuarioWebAuthService;
import com.mau.spring.model.dto.LoginDTO;
import com.mau.spring.model.dto.TokenDTO;
import com.mau.spring.model.dto.UsuarioWebDTO;
import com.mau.spring.repository.UsuarioWebRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarioWeb")
final class UsuarioWebController {
    private final UsuarioWebAuthService usuarioWebAuthService;
    private final UsuarioWebRepository usuarioWebRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioWebDTO body) {
        usuarioWebRepository.save(new UsuarioWeb(body.getNombre(), body.getApellido(), body.getEmail(), body.getPassword()));
        Optional<String> stringOptional = usuarioWebAuthService.login(body.getEmail(), body.getPassword());
        if (stringOptional.isPresent()){
            return ResponseEntity.ok(new TokenDTO(stringOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email y contraseña invalidos");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        Optional<String> stringOptional = usuarioWebAuthService.login(body.getEmail(), body.getPassword());
        if (stringOptional.isPresent()){
            return ResponseEntity.ok(new TokenDTO(stringOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email y contraseña invalidos");
        }
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent(@AuthenticationPrincipal UsuarioWeb user) {
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UsuarioWeb user) {
        usuarioWebAuthService.logout(user);
        return ResponseEntity.ok(true);
    }
}

