package edu.fi.mdp.celiacos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Alimento alimento;
    private double cantidad;

    @ManyToOne
    private UnidadDeMedida unidadDeMedida;
}
