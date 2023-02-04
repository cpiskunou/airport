package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
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
//@Tag(name = "Users", description = "Methods for work with users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    //   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
//    @Operation(summary = "Information about all users")
    public List<UserDTO> findAll() {
        return mapper.toDTO( service.findAll() );
    }

 //   @PreAuthorize("hasUser(#id) and hasRole('USER')")
 //   @PreAuthorize("hasUser(#id) and hasRole('USER')")
    @GetMapping("/{id}")
 //   @Operation(summary = "User information by id")
    public UserDTO findById(@PathVariable long id, @RequestParam(required = false) boolean withTickets) {
        return mapper.toDTO( service.findById(id, withTickets) );
    }

    @GetMapping("/search")
    public List<UserDTO> search(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO( service.search(user) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Validated(onCreate.class) UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO( service.create(user) );
    }

 //   @PreAuthorize("hasUser(#id) and hasRole('USER')")
    @PutMapping("/{id}")
    public UserDTO updateById(@PathVariable long id, @RequestBody @Validated(onUpdate.class) UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO( service.updateById(id, user) );
    }

 //   @PreAuthorize("hasUser(#id) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable int id) {
        service.removeById(id);
    }

}
