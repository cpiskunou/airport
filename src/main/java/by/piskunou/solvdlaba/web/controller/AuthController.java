package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.web.dto.AuthEntityDTO;
import by.piskunou.solvdlaba.service.AuthService;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import by.piskunou.solvdlaba.web.mapper.AuthMapper;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthEntityDTO register(@RequestBody @Validated(UserDTO.onRegister.class) UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        AuthEntity authEntity = authService.register(user);
        return authMapper.toDTO(authEntity);
    }


    @PostMapping("/authenticate")
    public AuthEntityDTO authenticate(@RequestBody @Validated(UserDTO.onAuth.class) UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        AuthEntity authEntity = authService.authenticate(user);
        return authMapper.toDTO(authEntity);
    }

    @PostMapping("/refresh")
    public AuthEntityDTO refresh(@RequestBody @Validated AuthEntityDTO authEntityDTO) {
        AuthEntity authEntity = authMapper.toEntity(authEntityDTO);
        authEntity = authService.refresh(authEntity);
        return authMapper.toDTO(authEntity);
    }


    @PostMapping("/new-access")
    public AuthEntityDTO newAccessToken(@RequestBody @Validated AuthEntityDTO authEntityDTO) {
        AuthEntity authEntity = authMapper.toEntity(authEntityDTO);
        authEntity = authService.getNewAccessToken(authEntity);
        return authMapper.toDTO(authEntity);
    }

}
