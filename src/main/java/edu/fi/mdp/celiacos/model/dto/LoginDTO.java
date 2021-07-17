package edu.fi.mdp.celiacos.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    @NotBlank(message = "El campo email no puede estar vacío")
    @Email(message = "Formato del email no es válido")
    @Size(max = 500)
    private String email;

    @NotBlank(message = "Debe ingresar su contraseña")
    private String password;
}
