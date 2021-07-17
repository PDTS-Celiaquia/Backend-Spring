package edu.fi.mdp.celiacos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UsuarioWebDTO {
    @NotBlank(message = "Falta nombre")
    private String nombre;
    @NotBlank(message = "Falta apellido")
    private String apellido;

    @Email(message = "El campo email no puede estar vacío")
    private String email;

    @NotBlank(message = "Debe ingresar su contraseña")
    private String password;
}
