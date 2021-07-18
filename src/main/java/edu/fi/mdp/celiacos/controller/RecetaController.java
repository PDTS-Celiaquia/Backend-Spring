package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.model.entity.Receta;
import edu.fi.mdp.celiacos.service.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name){
        return  ResponseEntity.ok(recetaService.getAll(name));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PostMapping("/")
    public ResponseEntity<?> addReceta(@RequestBody @Valid Receta nuevaReceta){
        return ResponseEntity.ok(recetaService.addReceta(nuevaReceta));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PostMapping("/modificar")
    public ResponseEntity<?> modificarReceta(@RequestBody @Valid Receta nuevaReceta){
        return ResponseEntity.ok(recetaService.modificarReceta(nuevaReceta));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @DeleteMapping("/{idReceta}")
    public ResponseEntity<?> deleteReceta(@PathVariable Integer idReceta){
        recetaService.deleteReceta(idReceta);
        return ResponseEntity.ok("OK");
    }


}
