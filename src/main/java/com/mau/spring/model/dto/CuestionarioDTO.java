package com.mau.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuestionarioDTO {
    private Long userId; //revisar
    private boolean celiaco;
    private String diagnostico;
    private boolean tratamiento;
    private boolean dietaSinTACC;
    private int frecuenciaTACC;
    private boolean verduras;
    private int frutas;
    private boolean carne;
}