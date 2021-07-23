package edu.fi.mdp.celiacos.controller;


import edu.fi.mdp.celiacos.exception.AlimentoNotFoundException;
import edu.fi.mdp.celiacos.model.dto.AccesibleDTO;
import edu.fi.mdp.celiacos.model.entity.Alimento;
import edu.fi.mdp.celiacos.service.AlimentoService;
import edu.fi.mdp.celiacos.utils.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/alimento")
public class AlimentoController {
    public static final String PUBLIC_IMG_ALIMENTOS = "public/img/alimentos/";

    private final AlimentoService alimentoService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(alimentoService.findAll(name));
    }

    @GetMapping("/{alimentoId}")
    public ResponseEntity<?> getAlimentoById(
            @PathVariable Long alimentoId
    ) throws AlimentoNotFoundException {
        return ResponseEntity.ok(alimentoService.getById(alimentoId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PostMapping("/")
    public ResponseEntity<?> addAlimento(@RequestBody @Valid Alimento nuevoAlimento) {
        return ResponseEntity.ok(alimentoService.save(nuevoAlimento));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PostMapping("/cargarTablas")
    public void cargarTablas() {
        alimentoService.cargarTablas();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PatchMapping("/{alimentoId}/accesible")
    public ResponseEntity<?> setAccesible(
            @PathVariable Long alimentoId, @RequestBody @Valid AccesibleDTO accesibleDTO
    ) {
        alimentoService.setEsAccesible(alimentoId, accesibleDTO);
        return ResponseEntity.ok("OK");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERARIO')")
    @PatchMapping("/{alimentoId}/imagen")
    public ResponseEntity<?> setImagen(
            @PathVariable Long alimentoId, @RequestParam("image") MultipartFile multipartFile
    ) throws AlimentoNotFoundException, IOException {
        String[] temp = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())).split("\\.");
        String extension = temp[temp.length - 1];

        String fileName = alimentoId + "." + extension;

        FileUploadUtil.saveFile(PUBLIC_IMG_ALIMENTOS, fileName, multipartFile);

        return ResponseEntity.ok(alimentoService.setImagen(alimentoId, fileName));
    }

}
