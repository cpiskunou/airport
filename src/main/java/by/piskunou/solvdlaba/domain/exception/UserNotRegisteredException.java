package by.piskunou.solvdlaba.domain.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
