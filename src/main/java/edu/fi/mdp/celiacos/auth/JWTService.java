package edu.fi.mdp.celiacos.auth;


import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Service
public final class JWTService implements Clock {
    private static final String DOT = ".";

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration-sec}")
    private int expirationSec;

    @Value("${jwt.clock-skew-sec}")
    private int clockSkewSec;

    @Value("${jwt.secret}")
    private String secretKey;

    public String expiring(final Map<String, String> attributes) {
        return newToken(attributes, expirationSec);
    }

    private String newToken(final Map<String, String> attributes, final int expiresInSec) {
        final Claims claims = Jwts.claims().setIssuer(issuer).setIssuedAt(now());

        if (expiresInSec > 0) {
            claims.setExpiration(
                    Date.from(Instant.now().plus(expiresInSec, ChronoUnit.SECONDS))
            );
        }
        claims.putAll(attributes);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .compact();
    }

    public Map<String, String> verify(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec)
                .setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    public Map<String, String> untrusted(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec);

        // See: https://github.com/jwtk/jjwt/issues/135
        final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
    }

    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (final IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }

    @Override
    public Date now() {
        return new Date();
    }
}
