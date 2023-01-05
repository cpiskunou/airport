package by.piskunou.solvdlaba.web.validator;

import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AirplaneDTOValidator implements Validator {
    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneDTOValidator(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AirplaneDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
