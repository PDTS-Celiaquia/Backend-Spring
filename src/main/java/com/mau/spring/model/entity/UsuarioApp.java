package com.mau.spring.model.entity;

import com.mau.spring.model.enums.FrecuenciaGluten;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
public class UsuarioApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private Date fecha_de_nacimiento;
    private boolean es_celiaco;
    private Date fecha_diagnostico_celiaquia;
    private boolean en_tratamiento_medico;
    private boolean sigue_dieta_sin_gluten;
    private boolean come_carnes;
    private boolean come_verduras;
    private boolean come_frutas;
    private ZonedDateTime fecha_ultima_actividad;
    private FrecuenciaGluten frecuencia_ingestion_gluten;
}
