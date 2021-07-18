package edu.fi.mdp.celiacos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "alimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {
    @Id
    private Long numero;
    private String nombre;
    private String clasif; // carnes, cereales, etc. Son las diferentes tablas
    private String genero_especie_variedad;//algunas tablas de alimento lo tienen

    private double energia_kJ;
    private double agua;
    private double proteina;
    private double grasa_total;
    private double carbohidrato_total;
    private double carbohidrato_disponible;
    private double fibra_dietetica;
    private double ceniza;
    private double sodio;
    private double potasio;
    private double calcio;
    private double fosforo;
    private double hierro;
    private double zinc;
    private double tiamina;
    private double rivoflavina;
    private double niacina;
    private double vitamina_c;
    private double acidos_grasos_saturados;
    private double acidos_grasos_monoinsaturados;
    private double acidos_grasos_poliinsaturados;
    private double colesterol;

    private Boolean esAccesible;
    private String imagen;
}
