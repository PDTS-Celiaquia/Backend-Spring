package com.mau.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cuestionario {
    @GeneratedValue
    @Id
    private Long cuestionarioId;
    private Long usuarioId;         //TODO Es necesario identificar al usuario que completó el cuestionario? o es anonimo?

    private boolean celiaco;        // ¿Es celíaco?
    private String diagnostico;     // Hace cuánto tiempo le diagnosticaron la enfermedad //TODO (¿longitud máxima? ¿no podría ser cantidad de años?)
    private boolean tratamiento;    // ¿Concurre a un hospital público para ser tratado por su celiaquía?
    private boolean dietaSinTACC;   // ¿Usted considera que adhiere correctamente a la dieta libre de gluten?
    private int frecuenciaTACC;     // ¿Con qué frecuencia usted cree que ingiere gluten (ya sea voluntaria o involuntariamente)
    //  0: Menos de 1 veces al año
    //  1: Algunas veces en el año
    //  2: 1 vez al mes
    //  3: Algunas veces al mes
    //  4: 1 vez a la semana
    //  5: Varias veces en la semana
    private boolean verduras;       // ¿Usted incluye verduras tanto en almuerzo como en cena?

    private int frutas;             // ¿Cuántas frutas consumes a diario? //TODO (¿en qué unidad se responde esta pregunta?)
    // TODO Analizar si podria ser un booleano como el resto de las comidas

    private boolean carne;          // Usted consume carnes (pollo, vaca, pescado) a diario?  Sí/No
    private ZonedDateTime timestamp;

    public Cuestionario(Long usuarioId, boolean celiaco, String diagnostico, boolean tratamiento, boolean dietaSinTACC, int frecuenciaTACC, boolean verduras, int frutas, boolean carne) {
        this.usuarioId = usuarioId;
        this.celiaco = celiaco;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.dietaSinTACC = dietaSinTACC;
        this.frecuenciaTACC = frecuenciaTACC;
        this.verduras = verduras;
        this.frutas = frutas;
        this.carne = carne;
        timestamp = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
    }
}