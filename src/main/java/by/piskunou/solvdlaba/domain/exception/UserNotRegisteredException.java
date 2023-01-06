package by.piskunou.solvdlaba.domain.exception;

import org.springframework.validation.BindException;

public class UserNotRegisteredException extends BindException {
    public UserNotRegisteredException(String message) {
        super(message);
    }
}
