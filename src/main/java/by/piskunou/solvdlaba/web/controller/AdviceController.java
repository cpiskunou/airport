package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.web.dto.MyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(UserNotRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MyErrorResponse handleResourceNotFoundException(UserNotRegisteredException e) {
        return new MyErrorResponse(e.getMessage())
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<MyErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<MyErrorResponse> myErrorResponseList = new ArrayList<>();
        for(FieldError fieldError: e.getFieldErrors()) {
            myErrorResponseList.add(new MyErrorResponse(fieldError, fieldError.getDefaultMessage()));
        }
        return myErrorResponseList;
    }
}
