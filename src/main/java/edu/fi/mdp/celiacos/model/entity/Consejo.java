package edu.fi.mdp.celiacos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consejos")
@AllArgsConstructor
@NoArgsConstructor

public class Consejo {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "titulo")
    private String titulo;

    @Size(max = 5000)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 8192)
    @Column(name = "enlace")
    private String enlace;

}
