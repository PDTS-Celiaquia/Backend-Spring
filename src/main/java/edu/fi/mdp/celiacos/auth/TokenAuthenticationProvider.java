package edu.fi.mdp.celiacos.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication) {
        Object token = authentication.getCredentials();

        return Optional.ofNullable(token)
                .map(String::valueOf)
                .flatMap(tokenAuthenticationService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }
}