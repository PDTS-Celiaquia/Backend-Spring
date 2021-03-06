package edu.fi.mdp.celiacos.model.entity;

import edu.fi.mdp.celiacos.auth.Usuario;
import edu.fi.mdp.celiacos.model.dto.request.CuestionarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "cuestionarios")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO Es necesario identificar al usuario que completó el cuestionario? o es anonimo?
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    // ¿Es celíaco?
    @Column(name = "celiaco")
    private boolean celiaco;

    // Hace cuánto tiempo le diagnosticaron la enfermedad
    //TODO (¿longitud máxima? ¿no podría ser cantidad de años?)
    @Size(max = 1200)
    @Column(name = "diagnostico", length = 1200)
    private String diagnostico;

    // ¿Concurre a un hospital público para ser tratado por su celiaquía?
    @Column(name = "tratamiento")
    private boolean tratamiento;

    // ¿Usted considera que adhiere correctamente a la dieta libre de gluten?
    @Column(name = "dieta_sintacc")
    private boolean dietaSinTACC;

    // ¿Con qué frecuencia usted cree que ingiere gluten (ya sea voluntaria o involuntariamente)
    //  0: Menos de 1 veces al año
    //  1: Algunas veces en el año
    //  2: 1 vez al mes
    //  3: Algunas veces al mes
    //  4: 1 vez a la semana
    //  5: Varias veces en la semana
    @Min(0)
    @Max(5)
    @Column(name = "frecuenciaTACC")
    private int frecuenciaTACC;

    // ¿Usted incluye verduras tanto en almuerzo como en cena?
    @Column(name = "verduras")
    private boolean verduras;

    // ¿Cuántas frutas consumes a diario?
    // TODO (¿en qué unidad se responde esta pregunta?)
    // TODO Analizar si podría ser un booleano como el resto de las comidas
    @Min(0)
    @Column(name = "frutas")
    private int frutas;

    // Usted consume carnes (pollo, vaca, pescado) a diario?  Sí/No
    @Column(name = "carne")
    private boolean carne;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    public Cuestionario(Usuario usuario, CuestionarioDTO cuestionarioDTO) {
        this.usuario = usuario;
        BeanUtils.copyProperties(cuestionarioDTO, this);
    }
}