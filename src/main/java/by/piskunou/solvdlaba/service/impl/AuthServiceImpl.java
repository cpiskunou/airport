package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.domain.UserDetailsImpl;
import by.piskunou.solvdlaba.service.AuthService;
import by.piskunou.solvdlaba.service.JwtService;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    @Transactional
    public AuthEntity signUp(User user) {
        userService.create(user);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return new AuthEntity(accessToken, refreshToken);
    }

    @Override
    public AuthEntity login(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        Authentication authentication = authManager.authenticate(authInputToken);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return new AuthEntity(accessToken, refreshToken);
    }

    @Override
    public AuthEntity refresh(AuthEntity authEntity) {
        if(jwtService.isValidRefreshToken(authEntity.getRefreshToken())) {
            throw new AccessDeniedException("Access denied");
        }
        String username = jwtService.extractUsername(authEntity.getAccessToken());
        UserDetailsImpl userDetails = new UserDetailsImpl(userService.findByUsername(username));
        authEntity.setAccessToken(jwtService.generateAccessToken(userDetails));
        return authEntity;
    }

}
