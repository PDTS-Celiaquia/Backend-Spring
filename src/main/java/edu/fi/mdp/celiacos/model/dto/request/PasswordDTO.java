package edu.fi.mdp.celiacos.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordDTO {
    @NotBlank(message = "Debe ingresar su contraseña")
    @Size(min = 4)
    private String password;
}
