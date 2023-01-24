package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthEntity register(User user) {
        return null;
    }

    @Override
    public AuthEntity authenticate(User user) {
        return null;
    }

    @Override
    public AuthEntity refresh(AuthEntity authEntity) {
        return null;
    }

    @Override
    public AuthEntity getNewAccessToken(AuthEntity authEntity) {
        return null;
    }

//    private final UserService userService;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    @Override
//    @Transactional
//    public AuthEntity register(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(User.Role.USER);
//        userService.register(user);
//        UserDetails userDetails = new UserDetailsImpl(user);
//        String accessToken = jwtService.generateAccessToken(userDetails);
//        String refreshToken = jwtService.generateRefreshToken(userDetails);
//        return new AuthEntity(accessToken, refreshToken);
//    }
//
//    @Override
//    @Transactional
//    public AuthEntity authenticate(User user) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                user.getUsername(),
//                user.getPassword()));
//        user = userService.findByUsername(user.getUsername());
//        UserDetails userDetails = new UserDetailsImpl(user);
//        String accessToken = jwtService.generateAccessToken(userDetails);
//        String refreshToken = jwtService.generateRefreshToken(userDetails);
//        return new AuthEntity(accessToken, refreshToken);
//    }
//
//    @Override
//    public AuthEntity refresh(AuthEntity authEntity) {
//        return jwtService.refresh(authEntity);
//    }
//
//    @Override
//    public AuthEntity getNewAccessToken(AuthEntity authEntity) {
//        return jwtService.getNewAccessToken(authEntity);
//    }

}
