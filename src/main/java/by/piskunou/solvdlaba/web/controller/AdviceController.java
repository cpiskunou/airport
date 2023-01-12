package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.exception.ResourseAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourseNotExistsException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.web.dto.MyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler({ResourseNotExistsException.class, ResourseAlreadyExistsException.class,
                       UserNotRegisteredException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceNotUpdatedException(ResourseNotExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<MyErrorResponse> handleBindException(BindException e) {
        List<MyErrorResponse> myErrorResponseList = new ArrayList<>();
        for(FieldError fieldError: e.getFieldErrors()) {
            myErrorResponseList.add( new MyErrorResponse(fieldError.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return myErrorResponseList;
    }

}
