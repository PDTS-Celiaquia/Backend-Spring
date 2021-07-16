package edu.fi.mdp.celiacos.controller;

import edu.fi.mdp.celiacos.model.entity.Receta;
import edu.fi.mdp.celiacos.service.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;

    @PostMapping("/")
    public ResponseEntity<?> addReceta(@RequestBody @Valid Receta nuevaReceta){
        return ResponseEntity.ok(recetaService.addReceta(nuevaReceta));
    }

    @PostMapping("/modificar")
    public ResponseEntity<?> modificarReceta(@RequestBody @Valid Receta nuevaReceta){
        return ResponseEntity.ok(recetaService.modificarReceta(nuevaReceta));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name){
        return  ResponseEntity.ok(recetaService.getAll(name));
    }

    @DeleteMapping("/{idReceta}")
    public ResponseEntity<?> deleteReceta(@PathVariable Integer idReceta){
        recetaService.deleteReceta(idReceta);
        return ResponseEntity.ok("OK");
    }


}
