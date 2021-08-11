package edu.fi.mdp.celiacos.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.fi.mdp.celiacos.model.dto.request.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @Column(name = "nombre", length = 200)
    private String nombre;

    @Size(max = 200)
    @Column(name = "apellido", length = 200)
    private String apellido;

    @Size(max = 500)
    @Column(name = "email", length = 500)
    private String email;

    @JsonIgnore
    @Size(max = 500)
    @Column(name = "password", length = 500)
    private String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority")
    private Authority authority;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_alta")
    private Date fecha_alta;

    @JsonIgnore
    @Column(name = "fecha_baja")
    private Date fecha_baja;

    public Usuario(UsuarioDTO usuarioDTO, Authority authority) {
        BeanUtils.copyProperties(usuarioDTO, this);
        this.authority = authority;
    }

    @Override
    @JsonIgnore
    public Collection<Authority> getAuthorities() { //TODO investigar
        return List.of(authority);
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return fecha_baja == null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return fecha_baja == null;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return fecha_baja == null;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return fecha_baja == null;
    }

    public String getRoleName() {
        return authority.getAuthority();
    }
}





