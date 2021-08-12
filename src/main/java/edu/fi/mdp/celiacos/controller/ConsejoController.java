package edu.fi.mdp.celiacos.controller;
import edu.fi.mdp.celiacos.model.entity.Consejo;
import edu.fi.mdp.celiacos.service.ConsejoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/consejo")
public class ConsejoController {
    private final ConsejoService consejoService;

    @GetMapping("/{idConsejo}")
    public ResponseEntity<?> find(@PathVariable Long idConsejo){
        return ResponseEntity.ok(consejoService.findById(idConsejo));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PutMapping("/")
    public ResponseEntity<?> saveReceta(@RequestBody @Valid Consejo nuevoConsejo){
        return ResponseEntity.ok(consejoService.saveConsejo(nuevoConsejo));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @DeleteMapping("/{idReceta}")
    public ResponseEntity<?> deleteReceta(@PathVariable Long idReceta){
        consejoService.deleteConsejo(idReceta);
        return ResponseEntity.ok("OK");
    }


}
