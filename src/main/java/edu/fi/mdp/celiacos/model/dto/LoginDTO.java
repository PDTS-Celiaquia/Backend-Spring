package edu.fi.mdp.celiacos.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "El campo email no puede estar vacío")
    @Email(message = "Formato del email no es válido")
    private String email;
    @NotBlank(message = "Debe ingresar su contreña")
    private String password;
}
