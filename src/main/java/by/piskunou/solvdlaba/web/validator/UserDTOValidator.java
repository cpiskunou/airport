package by.piskunou.solvdlaba.web.validator;

import by.piskunou.solvdlaba.service.UserService;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDTOValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if(userService.findByUsername(userDTO.getUsername()).isPresent()) {
            errors.rejectValue("username", String.valueOf(HttpStatus.BAD_REQUEST), "Username is taken");
        }
    }
}
