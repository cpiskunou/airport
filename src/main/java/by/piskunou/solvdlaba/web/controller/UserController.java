package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.mapper.UserMapper;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import by.piskunou.solvdlaba.web.validator.UserDTOValidator;
import by.piskunou.solvdlaba.web.url.UserURLs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(UserURLs.BASE)
public class UserController {
    private final UserDTOValidator userDTOValidator;
    private final UserService userService;

    @Autowired
    public UserController(UserDTOValidator userDTOValidator, UserService userService) {
        this.userDTOValidator = userDTOValidator;
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll()
                          .stream()
                          .map(UserMapper.INSTANCE::toDTO)
                          .toList();
    }

    @GetMapping(UserURLs.ID)
    public UserDTO findById(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("This's no user with such id");
        }

        return UserMapper.INSTANCE.toDTO(user.get());
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        userDTOValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField() + " - " + error.getDefaultMessage() + ". ");
            }

            throw new UserNotRegisteredException(errorMessage.toString());
        }

        userService.save(UserMapper.INSTANCE.toEntity(userDTO));
    }

    @DeleteMapping(UserURLs.ID)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable int id) {
        userService.removeById(id);
    }
}
