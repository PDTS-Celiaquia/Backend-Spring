package edu.fi.mdp.celiacos.model.entity;

import edu.fi.mdp.celiacos.model.enums.TipoAlimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {
    @Id
    private Long id;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "clasificacion")
    private String clasif; // carnes, cereales, etc. Son las diferentes tablas

    @Column(name = "variedad")
    private String genero_especie_variedad;//algunas tablas de alimento lo tienen

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoAlimento tipo; //Tipo de alimento segun el nivel de la piramide nutricional
    //TODO Revisar con las nutricionistas que categorizacion sirve para el método del plato

    @Min(0)
    @Column(name = "energia_kJ")
    private double energia_kJ;

    @Min(0)
    @Column(name = "agua")
    private double agua;

    @Min(0)
    @Column(name = "proteina")
    private double proteina;

    @Min(0)
    @Column(name = "grasa_total")
    private double grasa_total;

    @Min(0)
    @Column(name = "carbohidrato_total")
    private double carbohidrato_total;

    @Min(0)
    @Column(name = "carbohidrato_disponible")
    private double carbohidrato_disponible;

    @Min(0)
    @Column(name = "fibra_dietetica")
    private double fibra_dietetica;

    @Min(0)
    @Column(name = "ceniza")
    private double ceniza;

    @Min(0)
    @Column(name = "sodio")
    private double sodio;

    @Min(0)
    @Column(name = "potasio")
    private double potasio;

    @Min(0)
    @Column(name = "calcio")
    private double calcio;

    @Min(0)
    @Column(name = "fosforo")
    private double fosforo;

    @Min(0)
    @Column(name = "hierro")
    private double hierro;

    @Min(0)
    @Column(name = "zinc")
    private double zinc;

    @Min(0)
    @Column(name = "tiamina")
    private double tiamina;

    @Min(0)
    @Column(name = "rivoflavina")
    private double rivoflavina;

    @Min(0)
    @Column(name = "niacina")
    private double niacina;

    @Min(0)
    @Column(name = "vitamina_c")
    private double vitamina_c;

    @Min(0)
    @Column(name = "acidos_grasos_saturados")
    private double acidos_grasos_saturados;

    @Min(0)
    @Column(name = "acidos_grasos_monoinsaturados")
    private double acidos_grasos_monoinsaturados;

    @Min(0)
    @Column(name = "acidos_grasos_poliinsaturados")
    private double acidos_grasos_poliinsaturados;

    @Min(0)
    @Column(name = "colesterol")
    private double colesterol;

    @NotNull
    @Column(name = "es_accesible")
    private Boolean esAccesible;

    @Column(name = "imagen", length = 500)
    private String imagen;
}
