package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.security.UserDetailsImpl;
import by.piskunou.solvdlaba.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt-secret}")
    private static String SECRET_KEY;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(Collections.emptyMap(), userDetails);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(Collections.emptyMap(), userDetails);
    }

    @Override
    public String generateAccessToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusWeeks(1).toInstant()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    public boolean isTokenExpired(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public AuthEntity refresh(AuthEntity authEntity) {
        UserDetails userDetails = new UserDetailsImpl(new User(extractUsername(authEntity.getRefreshToken())));
        String newRefreshToken = generateRefreshToken(userDetails);
        authEntity.setRefreshToken(newRefreshToken);
        return authEntity;
    }

    @Override
    public AuthEntity getNewAccessToken(AuthEntity authEntity) {
        if(isTokenExpired(authEntity.getRefreshToken())) {
            throw new AccessDeniedException("Access denied");
        }
        UserDetails userDetails = new UserDetailsImpl(new User(extractUsername(authEntity.getAccessToken())));
        String newAccessToken = generateAccessToken(userDetails);
        authEntity.setAccessToken(newAccessToken);
        return authEntity;
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSignInKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public ZonedDateTime extractExpiration(String token) {
        return ZonedDateTime.ofInstant(extractClaim(token, Claims::getExpiration).toInstant(), ZoneId.systemDefault());
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
