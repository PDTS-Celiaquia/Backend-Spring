package edu.fi.mdp.celiacos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioWebDTO extends LoginDTO {
    @NotBlank(message = "Falta nombre")
    private String nombre;

    @NotBlank(message = "Falta apellido")
    private String apellido;
}
