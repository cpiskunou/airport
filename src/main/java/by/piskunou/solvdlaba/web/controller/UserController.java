package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import by.piskunou.solvdlaba.web.url.UserURLs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserURLs.BASE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                          .stream()
                          .map(UserMapper.INSTANCE::toDTO)
                          .toList();
    }

    @GetMapping(UserURLs.ID)
    public UserDTO findById(@PathVariable int id) {
        return UserMapper.INSTANCE.toDTO(userService.findById(id));
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Validated UserDTO userDTO) {
        userService.save(UserMapper.INSTANCE.toEntity(userDTO));
    }

    @DeleteMapping(UserURLs.ID)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable int id) {
        userService.removeById(id);
    }
}
