package com.mau.spring.controller;

import com.mau.spring.model.dto.CuestionarioDTO;
import com.mau.spring.service.CuestionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/cuestionario")
public class CuestionarioController {
    private final CuestionarioService cuestionarioService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody CuestionarioDTO cuestionario) {
        return ResponseEntity.ok(cuestionarioService.nuevoCuestionario(cuestionario));
    }

}
