package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.exception.ErrorResponse;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(UserNotRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleResourceNotFoundException(UserNotRegisteredException e) {
        return ErrorResponseBuilder(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return ErrorResponseBuilder(e.getMessage());
    }

    private ErrorResponse ErrorResponseBuilder(String message) {
        return new ErrorResponse(message, LocalDateTime.now());
    }
}
