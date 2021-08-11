package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.model.entity.Receta;
import edu.fi.mdp.celiacos.service.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name){
        return ResponseEntity.ok(recetaService.getAll(name));
    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<?> getAll(@PathVariable Long idReceta){
        return ResponseEntity.ok(recetaService.findById(idReceta));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PutMapping("/")
    public ResponseEntity<?> saveReceta(@RequestBody @Valid Receta nuevaReceta){
        return ResponseEntity.ok(recetaService.saveReceta(nuevaReceta));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @DeleteMapping("/{idReceta}")
    public ResponseEntity<?> deleteReceta(@PathVariable Long idReceta){
        recetaService.deleteReceta(idReceta);
        return ResponseEntity.ok("OK");
    }


}
