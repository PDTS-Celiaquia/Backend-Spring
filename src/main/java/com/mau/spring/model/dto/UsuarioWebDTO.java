package com.mau.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioWebDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
}
