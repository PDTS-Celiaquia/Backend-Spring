package com.mau.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngrediente;
    @ManyToOne
    private Alimento alimento;
    private double cantidad;

    @ManyToOne
    private UnidadDeMedida unidadDeMedida;
}
