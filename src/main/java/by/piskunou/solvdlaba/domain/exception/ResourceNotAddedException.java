package by.piskunou.solvdlaba.domain.exception;

import org.springframework.validation.BindException;

public class ResourceNotAddedException extends BindException {
    public ResourceNotAddedException(String message) {
        super(message);
    }
}
