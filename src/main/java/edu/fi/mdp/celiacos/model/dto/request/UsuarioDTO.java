package edu.fi.mdp.celiacos.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO extends LoginDTO {
    @NotBlank(message = "Falta nombre")
    private String nombre;

    @NotBlank(message = "Falta apellido")
    private String apellido;
}
