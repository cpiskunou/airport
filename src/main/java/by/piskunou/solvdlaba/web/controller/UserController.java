package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private static final String ID = "/{id}";

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                          .stream()
                          .map(UserMapper.INSTANCE::toDTO)
                          .toList();
    }

    @GetMapping(ID)
    public UserDTO findById(@PathVariable int id) {
        User user = userService.findById(id);

        return UserMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Validated UserDTO userDTO) {
        userService.create(UserMapper.INSTANCE.toEntity(userDTO));
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable int id) {
        userService.removeById(id);
    }
}
