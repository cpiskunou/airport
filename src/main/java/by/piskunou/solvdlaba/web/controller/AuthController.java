package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.AuthService;
import by.piskunou.solvdlaba.web.dto.AuthEntityDTO;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import by.piskunou.solvdlaba.web.mapper.AuthMapper;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Sign up new user")
    @Parameter(name = "dto", description = "Signed-up user")
    public AuthEntityDTO signUp(@RequestBody @Validated(UserDTO.onSignUp.class) UserDTO dto) {
        User user = userMapper.toEntity(dto);
        return authMapper.toDTO( service.signUp(user) );
    }

    @PostMapping("/login")
    @Operation(summary = "Login existing user")
    @Parameter(name = "dto", description = "Existing user")
    public AuthEntityDTO login(@RequestBody @Validated(UserDTO.onLogin.class) UserDTO dto) {
        User user = userMapper.toEntity(dto);
        return authMapper.toDTO( service.login(user) );
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh jwt access token by valid refresh jwt token")
    @Parameter(name = "authEntityDTO", description = "Entity with access and refresh jwt token")
    public AuthEntityDTO refresh(@RequestBody @Validated AuthEntityDTO authEntityDTO) {
        AuthEntity authEntity = authMapper.toEntity(authEntityDTO);
        return authMapper.toDTO( service.refresh(authEntity) );
    }

}
