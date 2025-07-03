package ru.dzhager3354.keeneye.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager {
    @Value("${token.signing.key}")
    private String jwtSecret;

    @Value("${token.time.expired}")
    private Long TOKEN_VALIDITY;

    private JwtParser parser;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        long millis = System.currentTimeMillis();

        return Jwts
                .builder()
                .claims().empty().add(claims)
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(millis))
                .expiration(new Date(millis + TOKEN_VALIDITY * 1000))
                .signWith(getKey())
                .compact();
    }

    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        final Claims claims = getClaims(token);

        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername())) && !isTokenExpired;
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = getClaims(token);

        return claims.getSubject();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(String token) {
        if (parser == null) {
            parser = Jwts
                    .parser()
                    .setSigningKey(getKey())
                    .build();
        }

        return parser
                .parseClaimsJws(token)
                .getBody();
    }
}
