package com.mau.spring.auth;


import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Service
public final class JWTService implements Clock {
    private static final String DOT = ".";

    private final String issuer;
    private final int expirationSec;
    private final int clockSkewSec;
    private final String secretKey;

    public JWTService(
                    @Value("${jwt.issuer:PDTS-Celiaquia}") final String issuer,
                    @Value("${jwt.expiration-sec:86400}") final int expirationSec,
                    @Value("${jwt.clock-skew-sec:300}") final int clockSkewSec,
                    @Value("${jwt.secret:3892ry23879ru2389ru93ry}") final String secret) {
        super();
        this.issuer = requireNonNull(issuer);
        this.expirationSec = expirationSec;
        this.clockSkewSec = clockSkewSec;
        this.secretKey = BASE64.encode(requireNonNull(secret));
    }

    public String permanent(final Map<String, String> attributes) {
        return newToken(attributes, 0);
    }

    public String expiring(final Map<String, String> attributes) {
        return newToken(attributes, expirationSec);
    }

    private String newToken(final Map<String, String> attributes, final int expiresInSec) {
        final Date now = new Date();
        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(now);

        if (expiresInSec > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.SECOND, expiresInSec);
            final Date expiresAt =  calendar.getTime();

            claims.setExpiration(expiresAt);
        }
        claims.putAll(attributes);

        return Jwts
                .builder()
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
