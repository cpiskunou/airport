package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                          .stream()
                          .map(mapper::toDTO)
                          .toList();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable long id) {
        User user = userService.findById(id);

        return mapper.toDTO(user);
    }

    @GetMapping("/{id}/tickets")
    public UserDTO findUserTickets(@PathVariable long id) {
        User user = userService.findUserTickets(id);

        return mapper.toDTO(user);
    }


    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO register(@RequestBody @Valid UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);

        user = userService.register(user);

        return mapper.toDTO(user);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUsernameById(@PathVariable long id, @RequestParam String username) {
        User user = userService.updateUsernameById(id, username);

        return mapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable int id) {
        userService.removeById(id);
    }

}
