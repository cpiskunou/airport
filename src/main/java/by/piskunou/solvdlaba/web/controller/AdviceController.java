package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.web.dto.ErrorResponseDTO;
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

    @ExceptionHandler(ResourceNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleResourceNotExistsException(ResourceNotExistsException e) {
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleResourceNotUpdatedException(ResourceNotExistsException e) {
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponseDTO> handleBindException(BindException e) {
        List<ErrorResponseDTO> errorResponseDTOS = new ArrayList<>();
        for(FieldError fieldError: e.getFieldErrors()) {
            errorResponseDTOS.add( new ErrorResponseDTO(fieldError.getObjectName() + "."
                    + fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return errorResponseDTOS;
    }

}
