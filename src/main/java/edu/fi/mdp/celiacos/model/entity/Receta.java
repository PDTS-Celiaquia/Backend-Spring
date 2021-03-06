package edu.fi.mdp.celiacos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "recetas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 400)
    @Column(name = "descripcion", length = 400)
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recetas_ingredientes")
    private Set<Ingrediente> ingredientes;

    @Min(1)
    @Column(name = "cantidad_platos")
    private Integer cantidadPlatos;

    @Size(max = 5000)
    @Column(name = "instrucciones", length = 5000)
    private String instrucciones;

    @Min(0)
    @Column(name = "energia_kj")
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

    @Column(name = "cantidad_alimentos_accesibles")
    private int cantidad_alimentos_accesibles;

    //Cual es el proposito de alimentos no accesibles?
    //Es esperado que la receta completa sea apta para un cel??aco
    @Column(name = "cantidad_alimentos_no_accesibles")
    private int cantidad_alimentos_no_accesibles;
}
