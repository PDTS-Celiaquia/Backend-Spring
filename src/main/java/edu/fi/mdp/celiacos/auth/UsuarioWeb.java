package edu.fi.mdp.celiacos.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "usuarios_web")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioWeb implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    @JsonIgnore
    private String password; //todo investigar

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_alta;

    private Date fecha_baja;

    public UsuarioWeb(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //TODO investigar
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return fecha_baja == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return fecha_baja == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return fecha_baja == null;
    }

    @Override
    public boolean isEnabled() {
        return fecha_baja == null;
    }
}





