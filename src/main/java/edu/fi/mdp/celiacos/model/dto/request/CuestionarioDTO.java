package edu.fi.mdp.celiacos.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CuestionarioDTO {

    private boolean celiaco;

    @Size(max = 1200)
    private String diagnostico;

    private boolean tratamiento;

    private boolean dietaSinTACC;

    @Min(0)
    @Max(5)
    private int frecuenciaTACC;

    private boolean verduras;

    @Min(0)
    private int frutas;

    private boolean carne;
}