package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotAddedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import by.piskunou.solvdlaba.web.url.AirplaneURLs;
import by.piskunou.solvdlaba.web.validator.AirplaneDTOValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(AirplaneURLs.BASE)
public class AirplaneController {
    private final AirplaneService airplaneService;
    private final AirplaneDTOValidator airplaneDTOValidator;

    @Autowired
    public AirplaneController(AirplaneService airplaneService, AirplaneDTOValidator airplaneDTOValidator) {
        this.airplaneService = airplaneService;
        this.airplaneDTOValidator = airplaneDTOValidator;
    }

    @GetMapping(AirplaneURLs.ID)
    public AirplaneDTO findById(@PathVariable int id) {
        Optional<Airplane> airplane = airplaneService.findById(id);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("This's no  with such id");
        }
        return AirplaneMapper.INSTANCE.toDTO(airplane.get());
    }

    @GetMapping("/{model}")
    public AirplaneDTO findByModel(@PathVariable String model) {
        Optional<Airplane> airplane = airplaneService.findByModel(model);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("This's no such model of airplane");
        }

        return AirplaneMapper.INSTANCE.toDTO(airplane.get());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody @Valid AirplaneDTO airplaneDTO, BindingResult bindingResult) {
        airplaneDTOValidator.validate(airplaneDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField() + " - " + error.getDefaultMessage() + ". ");
            }

            throw new ResourceNotAddedException(errorMessage.toString());
        }

        airplaneService.save(AirplaneMapper.INSTANCE.toEntity(airplaneDTO));
    }
}
