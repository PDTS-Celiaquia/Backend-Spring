package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.model.entity.UnidadDeMedida;
import edu.fi.mdp.celiacos.service.UnidadesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/unidades")
public class UnidadesController {
    private final UnidadesService unidadesService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(unidadesService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> addUnidad(@RequestBody UnidadDeMedida nuevaUnidad) {
        return ResponseEntity.ok(unidadesService.addUnidad(nuevaUnidad));
    }
}
