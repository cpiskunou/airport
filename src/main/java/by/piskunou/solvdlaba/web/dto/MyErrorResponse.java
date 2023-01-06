package by.piskunou.solvdlaba.web.dto;

import lombok.AllArgsConstructor;
import org.springframework.validation.FieldError;

@AllArgsConstructor
public class MyErrorResponse {
    private FieldError fieldError;
    private String message;
}
