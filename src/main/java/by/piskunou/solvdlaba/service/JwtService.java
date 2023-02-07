package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.UserDetailsImpl;

public interface JwtService {

    String extractUsername(String jwt);

    String generateAccessToken(UserDetailsImpl userDetails);

    String generateRefreshToken(UserDetailsImpl userDetails);

    boolean isValidAccessToken(String jwt);

    boolean isValidRefreshToken(String jwt);

}
