package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.AuthEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    String generateAccessToken(Map<String, Object> claims, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails);

    String extractUsername(String token);

    Claims extractAllClaims(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    AuthEntity refresh(AuthEntity authEntity);

    AuthEntity getNewAccessToken(AuthEntity authEntity);

}
