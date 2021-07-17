package edu.fi.mdp.celiacos.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private AuthorityEnum authorityEnum;

    @Override
    public String getAuthority() {
        return authorityEnum.name();
    }
}
