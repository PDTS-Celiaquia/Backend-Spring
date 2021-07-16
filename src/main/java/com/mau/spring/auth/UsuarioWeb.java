package com.mau.spring.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuarios_web")
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

    private String password; //todo investigar

    private Date fecha_alta;

    private Date fecha_baja; //nulo en caso de cuenta activa

    @JsonCreator
    public UsuarioWeb(@JsonProperty("nombre") String nombre,
                      @JsonProperty("apellido") String apellido,
                      @JsonProperty("email") String email,
                      @JsonProperty("password") String password) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fecha_alta = new Date();
        this.fecha_baja = null;
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





