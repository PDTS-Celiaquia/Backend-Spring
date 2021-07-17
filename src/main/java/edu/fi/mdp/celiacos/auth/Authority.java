package edu.fi.mdp.celiacos.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @JsonIgnore
    private AuthorityEnum authorityEnum;

    @Override
    public String getAuthority() {
        return authorityEnum.name();
    }
}
